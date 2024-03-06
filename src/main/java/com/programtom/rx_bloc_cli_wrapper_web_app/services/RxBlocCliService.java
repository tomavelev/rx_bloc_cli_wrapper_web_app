package com.programtom.rx_bloc_cli_wrapper_web_app.services;

import com.programtom.rx_bloc_cli_wrapper_web_app.models.CiCd;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.Project;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.RealTimeCommunication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;

@Service
public class RxBlocCliService {

    @Value("${building_directory}")
    private String buildingDirectory;

    @Value("${flutter_directory}")
    private String flutterDirectory;

    public File create(Project project) {

        File file = new File(buildingDirectory, project.getProjectName());
        file.mkdirs();

        List<String> commands = new ArrayList<>(Arrays.asList("rx_bloc_cli", "create", "."
                , "--project-name ", project.getProjectName()
                , "--organisation ", project.getOrganisation()));

        commands.add("--" + (project.isAnalytics() ? "" : "no-") + "enable-analytics");
        commands.add(("--" + (project.isCounterExample() ? "" : "no-") + "enable-feature-counter"));
        commands.add(("--" + (project.isDeeplinksExample() ? "" : "no-") + "enable-feature-deeplinks"));
        commands.add(("--" + (project.isWidgetToolkitExample() ? "" : "no-") + "enable-feature-widget-toolkit"));
        commands.add(("--" + (project.isCustomLogin() ? "" : "no-") + "enable-login"));
        commands.add(("--" + (project.isSocialLogins() ? "" : "no-") + "enable-social-logins"));
        commands.add(("--" + (project.isAuthMatrix() ? "" : "no-") + "enable-auth-matrix"));
        commands.add(("--" + (project.isPinCode() ? "" : "no-") + "enable-pin-code"));
        commands.add(("--" + (project.isOtp() ? "" : "no-") + "enable-otp"));
        commands.add(("--" + (project.isDevMenu() ? "" : "no-") + "enable-dev-menu"));
        commands.addAll(Arrays.asList("--realtime-communication", RealTimeCommunication.values()[(project.getRealtimeCommunication())].name()));
        commands.addAll(Arrays.asList("--cicd", CiCd.values()[(project.getCiCd())].name()));

        commands.add(("--" + (project.isPatrol() ? "" : "no-") + "enable-patrol"));
        commands.add(("--" + (project.isChangeLanguage() ? "" : "no-") + "enable-change-language"));

        commands.add("--no-interactive");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("export PATH=");
        stringBuffer.append(flutterDirectory);
        stringBuffer.append(":$PATH\n");
        stringBuffer.append("export PATH=\"$PATH\":\"$HOME/.pub-cache/bin\"");
        stringBuffer.append("\n");

        stringBuffer.append("zip -r ").append(project.getProjectName()).append(".zip .\n");
        commands.forEach(x->{
            stringBuffer.append(x).append(" ");
        });
        File f = new File(file,"createCommand.sh");
        try {
            f.createNewFile();

            Set<PosixFilePermission> perms = new HashSet<>();
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.OWNER_EXECUTE);
            Files.setPosixFilePermissions(f.toPath(), perms);

            Files.writeString(f.toPath(), stringBuffer.toString(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProcessBuilder pb = new ProcessBuilder("sh","createCommand.sh");
        pb.directory(file);
        pb.inheritIO();

        try {
            Process exec = pb.start();
            int exitCode = exec.waitFor();
            if(exitCode == 0) {
                return new File(file, project.getProjectName()+".zip");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File build(Project project) {
        File file = new File(buildingDirectory, project.getProjectName());
        return new File(file, project.getProjectName()+".zip");
    }
}
