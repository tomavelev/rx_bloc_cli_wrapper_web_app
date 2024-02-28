package com.programtom.rx_bloc_cli_wrapper_web_app.views;

import com.programtom.rx_bloc_cli_wrapper_web_app.i18n.RxBlocCliWrapperWebAppMessages;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.Project;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.RealTimeCommunication;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;


public class ProjectAddEditComponent extends BaseAddEditComponent<Project> {

    protected TextField idField;
    protected TextField projectNameField;
    protected TextField organisationField;
    protected Checkbox analyticsField;
    protected Checkbox counterExampleField;
    protected Checkbox deeplinksExampleField;
    protected Checkbox widgetToolkitExampleField;
    protected Checkbox customLoginField;
    protected Checkbox socialLoginsField;
    protected Checkbox changeLanguageField;
    protected Checkbox patrolField;
    protected ComboBox<RealTimeCommunication> realtimeCommunicationField;
    protected List<String> realtimeCommunicationList;
    protected Checkbox devMenuField;
    protected Checkbox otpField;
    protected Checkbox authMatrixField;
    protected Checkbox pinCodeField;
    protected ComboBox<String> ciCdField;
    protected List<String> ciCdList;

    public ProjectAddEditComponent(Project i, ProjectListComponent list) {
        super(i, list);
    }

    protected void initObjectFields(Project project) {
        idField = new TextField(RxBlocCliWrapperWebAppMessages.getString("{{Id}}"));
        if (object.getId() != null) {
            idField.setValue(object.getId());
        }
        idField.setWidth("100%");
        idField.setEnabled(false);
        add(idField);
        projectNameField = new TextField(RxBlocCliWrapperWebAppMessages.getString("{{ProjectName}}"));
        if (object.getProjectName() != null) {
            projectNameField.setValue(object.getProjectName());
        }
        projectNameField.setWidth("100%");
        add(projectNameField);
        organisationField = new TextField(RxBlocCliWrapperWebAppMessages.getString("{{Organisation}}"));
        if (object.getOrganisation() != null) {
            organisationField.setValue(object.getOrganisation());
        }
        organisationField.setWidth("100%");
        add(organisationField);
        analyticsField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{Analytics}}"), object.isAnalytics());
        analyticsField.setWidth("100%");
        add(analyticsField);
        counterExampleField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{CounterExample}}"), object.isCounterExample());
        counterExampleField.setWidth("100%");
        add(counterExampleField);
        deeplinksExampleField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{DeeplinksExample}}"), object.isDeeplinksExample());
        deeplinksExampleField.setWidth("100%");
        add(deeplinksExampleField);
        widgetToolkitExampleField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{WidgetToolkitExample}}"), object.isWidgetToolkitExample());
        widgetToolkitExampleField.setWidth("100%");
        add(widgetToolkitExampleField);
        customLoginField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{CustomLogin}}"), object.isCustomLogin());
        customLoginField.setWidth("100%");
        add(customLoginField);
        socialLoginsField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{SocialLogins}}"), object.isSocialLogins());
        socialLoginsField.setWidth("100%");
        add(socialLoginsField);
        changeLanguageField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{ChangeLanguage}}"), object.isChangeLanguage());
        changeLanguageField.setWidth("100%");
        add(changeLanguageField);
        patrolField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{Patrol}}"), object.isPatrol());
        patrolField.setWidth("100%");
        add(patrolField);
        realtimeCommunicationField = new ComboBox<>(RxBlocCliWrapperWebAppMessages.getString("{{RealtimeCommunication}}"));
        realtimeCommunicationList = new ArrayList<>();
        realtimeCommunicationList.add(RxBlocCliWrapperWebAppMessages.getString("none"));
        realtimeCommunicationList.add(RxBlocCliWrapperWebAppMessages.getString("sse"));
        realtimeCommunicationField.setItems(RealTimeCommunication.values());
        realtimeCommunicationField.setValue(RealTimeCommunication.values()[object.getRealtimeCommunication()]);
        realtimeCommunicationField.setWidth("100%");
        add(realtimeCommunicationField);
        devMenuField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{DevMenu}}"), object.isDevMenu());
        devMenuField.setWidth("100%");
        add(devMenuField);
        otpField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{Otp}}"), object.isOtp());
        otpField.setWidth("100%");
        add(otpField);
        authMatrixField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{AuthMatrix}}"), object.isAuthMatrix());
        authMatrixField.setWidth("100%");
        add(authMatrixField);
        pinCodeField = new Checkbox(RxBlocCliWrapperWebAppMessages.getString("{{PinCode}}"), object.isPinCode());
        pinCodeField.setWidth("100%");
        add(pinCodeField);
        ciCdField = new ComboBox<>(RxBlocCliWrapperWebAppMessages.getString("{{CiCd}}"));
        ciCdList = new ArrayList<>();
        ciCdList.add(RxBlocCliWrapperWebAppMessages.getString("none"));
        ciCdList.add(RxBlocCliWrapperWebAppMessages.getString("github"));
        ciCdList.add(RxBlocCliWrapperWebAppMessages.getString("fastlane"));
        ciCdField.setItems(ciCdList.toArray(new String[0]));
        ciCdField.setValue(ciCdList.get(object.getCiCd()));
        ciCdField.setWidth("100%");
        add(ciCdField);


        focusFirst();

    }

    protected void onSaveClick() {
        object.setProjectName(projectNameField.getValue());
        object.setOrganisation(organisationField.getValue());
        object.setAnalytics(analyticsField.getValue());
        object.setCounterExample(counterExampleField.getValue());
        object.setDeeplinksExample(deeplinksExampleField.getValue());
        object.setWidgetToolkitExample(widgetToolkitExampleField.getValue());
        object.setCustomLogin(customLoginField.getValue());
        object.setSocialLogins(socialLoginsField.getValue());
        object.setChangeLanguage(changeLanguageField.getValue());
        object.setPatrol(patrolField.getValue());
        object.setRealtimeCommunication((realtimeCommunicationField.getValue().ordinal()));
        object.setDevMenu(devMenuField.getValue());
        object.setOtp(otpField.getValue());
        object.setAuthMatrix(authMatrixField.getValue());
        object.setPinCode(pinCodeField.getValue());
        object.setCiCd(ciCdList.indexOf(ciCdField.getValue()));

        list.save(object);
        back();
    }

    protected void focusFirst() {
        projectNameField.focus();
    }
}
