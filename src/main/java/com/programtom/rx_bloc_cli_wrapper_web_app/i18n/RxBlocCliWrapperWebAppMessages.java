package com.programtom.rx_bloc_cli_wrapper_web_app.i18n;

import com.programtom.rx_bloc_cli_wrapper_web_app.utils.SpringContext;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class RxBlocCliWrapperWebAppMessages {
	static MessageSource messageSource;

	public static String getString(String key) {

		if (messageSource == null) {
			messageSource = SpringContext.getBean("rx_bloc_cli_wrapper_web_app", MessageSource.class);
		}
		return messageSource.getMessage(key, null, key, Locale.US);
	}
}
