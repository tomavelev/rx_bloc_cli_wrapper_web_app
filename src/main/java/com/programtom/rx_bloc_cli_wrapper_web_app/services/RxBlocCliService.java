package com.programtom.rx_bloc_cli_wrapper_web_app.services;

import com.programtom.rx_bloc_cli_wrapper_web_app.models.CiCd;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.Project;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.RealTimeCommunication;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RxBlocCliService {


    public void create(Project project) {
        File file = new File("/mnt/host_folder/", project.getProjectName());
        file.mkdirs();
//
        List<String> commands = new ArrayList<>(Arrays.asList("/root/.pub-cache/bin/rx_bloc_cli", "create", "."
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
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("/root/.pub-cache/bin/rx_bloc_cli create . --project-name " + project.getProjectName() + " --organisation " + project.getOrganisation());
//
//
//         sb.append("--" + (project.isAnalytics() ? "" : "no-") + "enable-analytics ");
//         sb.append(("--" + (project.isCounterExample() ? "" : "no-") + "enable-feature-counter "));
//         sb.append(("--" + (project.isDeeplinksExample() ? "" : "no-") + "enable-feature-deeplinks "));
//         sb.append(("--" + (project.isWidgetToolkitExample() ? "" : "no-") + "enable-feature-widget-toolkit "));
//         sb.append(("--" + (project.isCustomLogin() ? "" : "no-") + "enable-login "));
//         sb.append(("--" + (project.isSocialLogins() ? "" : "no-") + "enable-social-logins "));
//         sb.append(("--" + (project.isAuthMatrix() ? "" : "no-") + "enable-auth-matrix "));
//         sb.append(("--" + (project.isPinCode() ? "" : "no-") + "enable-pin-code "));
//         sb.append(("--" + (project.isOtp() ? "" : "no-") + "enable-otp "));
//         sb.append(("--" + (project.isDevMenu() ? "" : "no-") + "enable-dev-menu "));
//         sb.append(("--realtime-communication "+ RealTimeCommunication.values()[(project.getRealtimeCommunication())].name())).append(" ");
//         sb.append(("--cicd "+ CiCd.values()[(project.getCiCd())].name())).append(" ");
////
//         sb.append(("--" + (project.isPatrol() ? "" : "no-") + "enable-patrol "));
//         sb.append(("--" + (project.isChangeLanguage() ? "" : "no-") + "enable-change-language "));
////
//         sb.append("--no-interactive");
//
//
        ProcessBuilder pb = new ProcessBuilder(commands);
        System.out.println(pb.command());
        pb.directory(file);
        pb.inheritIO();

        try {
            Process exec = pb.start();


//            Thread t = new Thread(() -> {
//                try (Reader reader = new BufferedReader(new InputStreamReader
//                        (exec.getInputStream(), StandardCharsets.UTF_8))) {
//                    int c = 0;
//                    while ((c = reader.read()) != -1) {
//                        writer.write(String.valueOf((char) c));
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//            });
//            t.setDaemon(true);
//            t.start();
            int waitFor = exec.waitFor();
//            int waitFor = exec.waitFor();
            System.out.println("waitFor : " + waitFor);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
