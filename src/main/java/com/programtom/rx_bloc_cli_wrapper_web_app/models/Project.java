package com.programtom.rx_bloc_cli_wrapper_web_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity(name = "Project")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Project extends BaseEntity {
    @Column(name = "user_id")
	private String userId;
    @Column(name = "project_name")
	private String projectName;
    @Column(name = "organisation")
	private String organisation;
    @Column(name = "analytics")
	private boolean analytics;
    @Column(name = "counter_example")
	private boolean counterExample;
    @Column(name = "deeplinks_example")
	private boolean deeplinksExample;
    @Column(name = "widget_toolkit_example")
	private boolean widgetToolkitExample;
    @Column(name = "custom_login")
	private boolean customLogin;
    @Column(name = "social_logins")
	private boolean socialLogins;
    @Column(name = "change_language")
	private boolean changeLanguage;
    @Column(name = "patrol")
	private boolean patrol;
    @Column(name = "realtime_communication")
	private int realtimeCommunication;
    @Column(name = "dev_menu")
	private boolean devMenu;
    @Column(name = "otp")
	private boolean otp;
    @Column(name = "auth_matrix")
	private boolean authMatrix;
    @Column(name = "pin_code")
	private boolean pinCode;
    @Column(name = "ci_cd")
	private int ciCd;

}

