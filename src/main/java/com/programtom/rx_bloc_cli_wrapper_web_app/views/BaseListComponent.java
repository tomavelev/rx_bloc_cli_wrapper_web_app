package com.programtom.rx_bloc_cli_wrapper_web_app.views;

import com.programtom.rx_bloc_cli_wrapper_web_app.i18n.RxBlocCliWrapperWebAppMessages;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.QueryResult;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.auth.Login;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListComponent<T> extends VerticalLayout {

    public static final int PAGE_SIZE = 20;

    protected Button first;
    protected Button previous;
    protected Button next;
    protected Button last;

    protected long offset = 0l;

    protected HorizontalLayout paging;
    protected List<T> list;
    protected VerticalLayout content;
    protected long totalCount;
    protected ListDataProvider<T> dataProvider;
    protected Text resultLabel;

    protected TextField search = new TextField();
    protected Button searchB = new Button("Search");

    public BaseListComponent() {
        super();
    }

    public BaseListComponent(Component... children) {
        super(children);
    }

    protected void defaultInit() {

        setWidth("100%");
        list = new ArrayList<>();
        content = new VerticalLayout();
        content.setWidthFull();
        content.setWidth("100%");
        resultLabel = new Text("");
        paging = new HorizontalLayout();
        paging.setWidth("100%");

        Button add = new Button(RxBlocCliWrapperWebAppMessages.getString("add"));

        first = new Button(RxBlocCliWrapperWebAppMessages.getString("first_page"));
        previous = new Button(RxBlocCliWrapperWebAppMessages.getString("previous_page"));
        next = new Button(RxBlocCliWrapperWebAppMessages.getString("next_page"));
        last = new Button(RxBlocCliWrapperWebAppMessages.getString("last_page"));

        first.addClickListener(e -> {
            offset = 0L;
            refreshStuff();
        });

        previous.addClickListener(e -> {
            offset = offset - PAGE_SIZE;
            if (offset < 0) {
                offset = 0L;
            }
            refreshStuff();
        });

        next.addClickListener(e -> {
            offset = offset + PAGE_SIZE;
            refreshStuff();
        });

        last.addClickListener(e -> {
            offset = totalCount - PAGE_SIZE;
            refreshStuff();
        });

        paging.add(first, previous, resultLabel, next, last);

        add.addClickListener(e -> {
            addEdit(newModelInstance());
        });

        searchB.addClickListener((ComponentEventListener<ClickEvent<Button>>) buttonClickEvent -> {
            offset = 0;
            refreshStuff();
        });
        search.addKeyPressListener(keyPressEvent -> {
            if (keyPressEvent.getKey().equals(Key.ENTER)) {
                offset = 0L;
               refreshStuff();
            }
        });

        add(new HorizontalLayout(new H1(getModelTitle()), add, search, searchB));
        add(content);
        add(paging);
        setSizeFull();

//        grid.addSelectionListener(e -> {
//            boolean isSomethingSelected = e.getAllSelectedItems().size() > 0;
//            relatedObjects.setVisible(isSomethingSelected);
//            editDelete.setVisible(isSomethingSelected);
//            onItemSelected();
//        });
        refreshStuff();
    }

    protected abstract T newModelInstance();

    protected void onItemSelected() {
    }

    protected String getModelTitle() {
        return RxBlocCliWrapperWebAppMessages.getString(modelClass().getSimpleName());
    }

    protected abstract void deleteRecord(T t);

    protected void refreshStuff() {

        QueryResult<T> sr = loadRecords(search.getValue());

        list.clear();
        list.addAll(sr.getList());
        totalCount = sr.getCount();

        content.removeAll();
        list.forEach(t ->
                content.add(item(t)));
//		grid.setHeightByRows(true);
        StringBuilder sb = new StringBuilder();
        if (totalCount > 0) {
            sb.append(offset + 1);
        } else {
            sb.append(offset);
        }
        sb.append(" - ");

        sb.append(offset + list.size());
        sb.append(" Total " + totalCount);

        resultLabel.setText(sb.toString());
        add(resultLabel);
//		resultLabel.setTitle(sb.toString());
        if (totalCount > list.size()) {
            if (offset > 0) {
                first.setEnabled(true);
                previous.setEnabled(true);
            } else {
                first.setEnabled(false);
                previous.setEnabled(false);
            }
            if (offset + PAGE_SIZE < totalCount) {

                next.setEnabled(true);
                last.setEnabled(true);

                long remaining = totalCount % PAGE_SIZE;

                if (remaining == 0) {
                    remaining = totalCount - PAGE_SIZE;
                } else {
                    remaining = totalCount - remaining;
                }
            } else {
                next.setEnabled(false);
                last.setEnabled(false);
            }
        } else {
            first.setEnabled(false);
            previous.setEnabled(false);
            next.setEnabled(false);
            last.setEnabled(false);
        }
    }

    protected abstract Component item(T t);

    protected abstract QueryResult<T> loadRecords(String value);

    protected abstract void addEdit(T model);

    protected abstract Class<T> modelClass();

    public void refresh() {
        refreshStuff();
    }

    public abstract void save(T object);

    public Login getAppUser() {
        return (Login) VaadinSession.getCurrent().getSession().getAttribute("user");
    }

    protected VerticalLayout objectActions(T object) {
        VerticalLayout vertical = new VerticalLayout();

        vertical.setWidth("");
        vertical.setAlignItems(Alignment.START);
        vertical.add(new Button("Edit", buttonClickEvent -> {
            addEdit(object);
        }));
        vertical.add(new Button("Remove", buttonClickEvent -> {
       
            Dialog dialogDelete = new Dialog();


            dialogDelete.setHeaderTitle("Remove Confirmation");
//            dialogDelete.addConfirmListener(confirmEvent -> {
//                dialogDelete.setVisible(false);
//                deleteRecord(object);
//            });
            dialogDelete.add(new Text("Do you confirm you want to delete " + displayName(object) + " ?"));

            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.add(new Button("Confirm", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent1 -> {
                deleteRecord(object);
                dialogDelete.setVisible(false);
                dialogDelete.close();

            }));
            horizontalLayout.add(new Button("No", (ComponentEventListener<ClickEvent<Button>>) buttonClickEvent1 -> {
                dialogDelete.setVisible(false);
                dialogDelete.close();

            }));
            dialogDelete.add(horizontalLayout);

            dialogDelete.setVisible(true);
            dialogDelete.open();

        }));
        return vertical;
    }
//    public void showItemDialog(T object, Event2<Dialog, VerticalLayout> additionalButtons) {
//
//        Dialog dialog = new Dialog();
//        dialog.setHeaderTitle(displayName(object) + " Actions");
//        VerticalLayout vertical = new VerticalLayout();
//
//        if (additionalButtons != null) {
//            additionalButtons.onEvent(dialog, vertical);
//        }
//        vertical.add(new Button("Edit", buttonClickEvent -> {
//            dialog.close();
//            addEdit(object);
//        }));
//        vertical.add(new Button("Remove", buttonClickEvent -> {
//            dialog.close();
//            deleteRecord(object);
//        }));
//
//        dialog.add(vertical);
//        dialog.addDialogCloseActionListener(dialogCloseActionEvent -> dialog.close());
//        dialog.open();
//    }

    protected abstract String displayName(T object);

    protected void addBack(HasComponents parent) {
        Button back = new Button(RxBlocCliWrapperWebAppMessages.getString("back"), e -> {
            BaseListComponent<T> self = this;
            HasComponents selfParent = (HasComponents) self.getParent().get();
            selfParent.remove(self);
            selfParent.add((Component) parent);
        });
        add(back);
    }
}


