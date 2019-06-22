package Z3;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.DateFormat;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;




public class Z3 extends Application {

    private TableView<Entry> table = new TableView<Entry>();
    private final ObservableList<Entry> data = FXCollections.observableArrayList();
    private final HBox hb = new HBox();

    protected static Locale currentLocale;
    protected static ResourceBundle messages;
    protected static String Language;
    Button button;
    TableColumn<Entry,String> descriptionCol;
    TableColumn<Entry,String> costCol;
    TableColumn<Entry,String> dateCol;
    TableColumn<Entry,String> variableCol;
    TextField addDescription;
    TextField addCost;
    TextField addDate;
    CheckBox cbox;
    Button addButton;
    Button summaryButton;
    
    public void change(){
        button.setText(messages.getString("changeLanguage"));
        table.setPlaceholder(new Label(messages.getString("emptySpreadsheet")));
        descriptionCol.setText(messages.getString("description"));
        costCol.setText(messages.getString("cost"));
        dateCol.setText(messages.getString("date"));
        variableCol.setText(messages.getString("cyclic"));
        addDescription.setPromptText(messages.getString("description"));
        addCost.setPromptText(messages.getString("cost"));
        addDate.setPromptText(messages.getString("date"));
        cbox.setText(messages.getString("cyclic"));
        addButton.setText(messages.getString("add"));
        summaryButton.setText(messages.getString("summaryButton"));
    }
    
    public static class Entry {

        private final SimpleStringProperty description, cost, date;
        private final Boolean cyclic;
        private Entry (String description, String cost, String date, Boolean cyclic) {
            this.description = new SimpleStringProperty(description);
            this.cost = new SimpleStringProperty(cost);
            this.date = new SimpleStringProperty(date);
            this.cyclic = cyclic;
        }

        public String getDescription () {
            return description.get();
        }

        public void setDescription (String desc) {
            description.set(desc);
        }

        public String getCost () {
            return formatCurrency(cost.get());
        }

        public void setCost (String c) {
            cost.set(c);
        }

        public String getDate () {
            String pattern = "dd.MM.yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, currentLocale);

            int style = DateFormat.SHORT;
            DateFormat df = DateFormat.getDateInstance(style, currentLocale);

            try {
                return df.format(simpleDateFormat.parse(date.get()));
            } catch (Exception e) { return date.get(); }
        }

        public void setDate (String d) {
            date.set(d);
        }

        public String getCyclic () {
            if (cyclic)
                return messages.getString("yes");
            else
                return messages.getString("no");
        }

        public static String formatCurrency (String c) {
            NumberFormat cf = NumberFormat.getCurrencyInstance(currentLocale);
            try {
                return cf.format(Double.valueOf(c));
            } catch (Exception e) { return c; }
        }

        public String getUnformattedCost () {
            return cost.get();
        }

        public Boolean getVariableAsBoolean () {
            return cyclic;
        }

    }
    
    @Override
    public void start (Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle(messages.getString("windowTitle"));
        stage.setWidth(453);
        stage.setHeight(535);
        
        button = new Button();
        button.setText(messages.getString("changeLanguage"));
        button.setMinWidth(100);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                if(Language == "DE"){
                    currentLocale = new Locale("pl", "PL");
                    Language = "PL";
                }
                else if(Language == "GB"){
                    currentLocale = new Locale("de", "DE");
                    Language = "DE";
                }
                else{
                    currentLocale = new Locale("en", "GB");
                    Language = "GB";
                }
                messages = ResourceBundle.getBundle("resources/MessagesBundle", currentLocale);
                change();
            }
        });
        
        table.setPlaceholder(new Label(messages.getString("emptySpreadsheet")));
        table.setEditable(true);

        descriptionCol = new TableColumn<Entry,String>(messages.getString("description"));
        descriptionCol.setMinWidth(100);
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Entry,String>("description"));
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Entry,String>>() {
                @Override
                public void handle (CellEditEvent<Entry,String> t) {
                    ((Entry) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setDescription(t.getNewValue());
                }
            }
        );

        costCol = new TableColumn<Entry,String>(messages.getString("cost"));
        costCol.setMinWidth(100);
        costCol.setCellValueFactory(new PropertyValueFactory<Entry, String>("cost"));
        costCol.setCellFactory(TextFieldTableCell.forTableColumn());
        costCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Entry,String>>() {
                @Override
                public void handle (CellEditEvent<Entry,String> t) {
                    ((Entry) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setCost(t.getNewValue());
                    t.getTableView().getColumns().get(0).setVisible(false);
                    t.getTableView().getColumns().get(0).setVisible(true);
                }
            }
        );

        dateCol = new TableColumn<Entry,String>(messages.getString("date"));
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(new PropertyValueFactory<Entry,String>("date"));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Entry,String>>() {
                @Override
                public void handle (CellEditEvent<Entry,String> t) {
                    ((Entry) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setCost(t.getNewValue());
                    t.getTableView().getColumns().get(0).setVisible(false);
                    t.getTableView().getColumns().get(0).setVisible(true);
                }
            }
        );


        variableCol = new TableColumn<Entry,String>(messages.getString("cyclic"));
        variableCol.setMinWidth(100);
        variableCol.setCellValueFactory(new PropertyValueFactory<Entry,String>("cyclic"));
        variableCol.setCellFactory(TextFieldTableCell.forTableColumn());
        variableCol.setOnEditStart(
            new EventHandler<CellEditEvent<Entry,String>>() {
                @Override
                public void handle (CellEditEvent<Entry,String> t) {
                    t.getTableView().edit(-1, null);
                }
            }
        );

        table.setItems(data);
        table.getColumns().add(descriptionCol);
        table.getColumns().add(costCol);
        table.getColumns().add(dateCol);
        table.getColumns().add(variableCol);


        addDescription = new TextField();
        addDescription.setPromptText(messages.getString("description"));
        addDescription.setMaxWidth(75);

        addCost = new TextField();
        addCost.setPromptText(messages.getString("cost"));
        addCost.setMaxWidth(75);

        addDate = new TextField();
        addDate.setPromptText(messages.getString("date"));
        addDate.setMaxWidth(75);

        cbox = new CheckBox(messages.getString("cyclic"));
        cbox.setSelected(false);

        addButton = new Button(messages.getString("add"));
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                String desc = addDescription.getText();
                String cost = addCost.getText();
                String date = addDate.getText();

                if (!desc.isEmpty() && !cost.isEmpty() && !date.isEmpty()) {
                    data.add(new Entry(
                            addDescription.getText(),
                            addCost.getText(),
                            addDate.getText(),
                            cbox.isSelected()));
                    addDescription.clear();
                    addCost.clear();
                    addDate.clear();
                }
            }
        });


        summaryButton = new Button(messages.getString("summaryButton"));
        summaryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                double variable = 0;
                double cyclic = 0;
                for (Entry ex : data) {
                    try {
                        double cost = Double.valueOf(ex.getUnformattedCost());
                        if (ex.getVariableAsBoolean())
                            variable += cost;
                        else
                            cyclic += cost;
                    } catch (Exception ignore) { }
                }
                String msg = messages.getString("variable") + ": " + Entry.formatCurrency(Double.toString(variable));
                msg += "\n" + messages.getString("cyclic") + ": " + Entry.formatCurrency(Double.toString(cyclic));
                Alert alert = new Alert(AlertType.INFORMATION, msg, ButtonType.OK);
                alert.setTitle(messages.getString("summaryWindowTitle"));
                alert.setHeaderText(messages.getString("summaryWindowHeader"));
                alert.showAndWait();
            }
        });

        final VBox adjust = new VBox();
        adjust.getChildren().addAll(cbox);
        adjust.setAlignment(Pos.CENTER);
        
        hb.getChildren().addAll(addDescription, addCost, addDate, adjust, addButton, summaryButton);
        hb.setSpacing(3);

        final HBox hbox = new HBox();
        Region blank = new Region();
        blank.setMinWidth(202);
        hbox.getChildren().addAll(button);
        hbox.setAlignment(Pos.CENTER);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(hbox, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }



    public static void main (String[] args) {
        String language;
        String country;

        if (args.length != 2) {
            language = new String("pl");
            country = new String("PL");
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }

        currentLocale = new Locale(language, country);
        messages = ResourceBundle.getBundle("resources/MessagesBundle", currentLocale);
        Language = language;
        launch(args);
    }



    
}
