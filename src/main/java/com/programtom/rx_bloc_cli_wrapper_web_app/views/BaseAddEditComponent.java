package com.programtom.rx_bloc_cli_wrapper_web_app.views;

import com.programtom.rx_bloc_cli_wrapper_web_app.i18n.RxBlocCliWrapperWebAppMessages;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class BaseAddEditComponent<T> extends VerticalLayout{

	protected T object;
	protected BaseListComponent<T> list;
	protected HasComponents parent2;

	public BaseAddEditComponent(T i, BaseListComponent<T> list2) {

		setWidth("100%");
		this.object = i;
		this.list = list2;
		this.parent2 = (HasComponents) list.getParent().get();

		initObjectFields(i);

		HorizontalLayout footer = new HorizontalLayout();
		footer.add(new Button(RxBlocCliWrapperWebAppMessages.getString("save"), e -> {
			onSaveClick();
		}));

		footer.add(new Button(RxBlocCliWrapperWebAppMessages.getString("cancel"), e -> {
			back();
		}));

		add(footer);

		parent2.remove(list);
		parent2.add(this);
		focusFirst();
	}

	protected abstract void initObjectFields(T t);

	protected abstract void focusFirst();

	protected abstract void onSaveClick();


	protected void back() {
		(parent2).remove(this);
		parent2.add(list);
		list.refresh();
	}
}
