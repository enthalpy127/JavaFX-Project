package Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.text.Format;

public class Help {

    public TableView<Student> mainTable = new TableView<>();

    public ObservableList<Student> data = FXCollections.observableArrayList();
//            FXCollections.observableArrayList(
//                    new Student(1, "Phone Myat", 1011, 89, 65,78,56,45,34, 367, 29),
//                    new Student(2, "Kyaw Kyaw", 1022, 67,56,87,45,76,78,409,18),
//                    new Student(3, "Hnin Su", 1033, 76,87,56,87,56,76,438,12)
//            );

    public void setUpMainTableColumns(){
        TableColumn<Student, Integer> colNo = new TableColumn<>("No");
        colNo.setPrefWidth(30);
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

        TableColumn<Student, String> colName = new TableColumn<>("Name");
        colName.setPrefWidth(100);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student, Integer> colID = new TableColumn<>("ID");
        colID.setPrefWidth(50);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colID.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student, Integer> colMyanmar = new TableColumn<>("Myanmar");
        colMyanmar.setPrefWidth(80);
        colMyanmar.setCellValueFactory(new PropertyValueFactory<>("myanmar"));
        colMyanmar.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student, Integer> colEnglish = new TableColumn<>("English");
        colEnglish.setPrefWidth(80);
        colEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));
        colEnglish.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student, Integer> colMathematics = new TableColumn<>("Mathematics");
        colMathematics.setPrefWidth(80);
        colMathematics.setCellValueFactory(new PropertyValueFactory<>("mathematics"));
        colMathematics.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student , Integer> colPhysics = new TableColumn<>("Physics");
        colPhysics.setPrefWidth(80);
        colPhysics.setCellValueFactory(new PropertyValueFactory<>("physics"));
        colPhysics.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student, Integer> colChemistry = new TableColumn<>("Chemistry");
        colChemistry.setPrefWidth(80);
        colChemistry.setCellValueFactory(new PropertyValueFactory<>("chemistry"));
        colChemistry.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student , Integer> colBiology = new TableColumn<>("Biology");
        colBiology.setPrefWidth(80);
        colBiology.setCellValueFactory(new PropertyValueFactory<>("biology"));
        colBiology.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student, Integer> colTotalMarks = new TableColumn<>("Total Marks");
        colTotalMarks.setPrefWidth(100);
        colTotalMarks.setCellValueFactory(new PropertyValueFactory<>("totalmarks"));
        colTotalMarks.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        TableColumn<Student, Integer> colRollNo = new TableColumn<>("Roll No");
        colRollNo.setPrefWidth(100);
        colRollNo.setCellValueFactory(new PropertyValueFactory<>("rollno"));
        colRollNo.setCellFactory(new FormattedTableCellFactory<>(TextAlignment.CENTER));

        mainTable.getColumns().addAll(colNo, colName, colID, colMyanmar, colEnglish, colMathematics, colPhysics, colChemistry, colBiology, colTotalMarks, colRollNo);
    }


    /**
     * Formatter for table cells: allows you to align table cell values left/right/center
     *
     * Example for alignment form http://docs.oracle.com/javafx/2/fxml_get_started/fxml_tutorial_intermediate.htm
     *
     * @param <S>
     * @param <T>
     */
    public static class FormattedTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

        private TextAlignment alignment = TextAlignment.LEFT;
        private Format format;

        public FormattedTableCellFactory() {
        }

        public FormattedTableCellFactory(TextAlignment alignment) {
            this.alignment = alignment;
        }

        public TextAlignment getAlignment() {
            return alignment;
        }

        public void setAlignment(TextAlignment alignment) {
            this.alignment = alignment;
        }

        public Format getFormat() {
            return format;
        }

        public void setFormat(Format format) {
            this.format = format;
        }

        @Override
        @SuppressWarnings("unchecked")
        public TableCell<S, T> call(TableColumn<S, T> p) {
            TableCell<S, T> cell = new TableCell<S, T>() {
                @Override
                public void updateItem(Object item, boolean empty) {
                    if (item == getItem()) {
                        return;
                    }
                    super.updateItem((T) item, empty);
                    if (item == null) {
                        super.setText(null);
                        super.setGraphic(null);
                    } else if (format != null) {
                        super.setText(format.format(item));
                    } else if (item instanceof Node) {
                        super.setText(null);
                        super.setGraphic((Node) item);
                    } else {
                        super.setText(item.toString());
                        super.setGraphic(null);
                    }
                }
            };
            cell.setTextAlignment(alignment);
            switch (alignment) {
                case CENTER:
                    cell.setAlignment(Pos.CENTER);
                    break;
                case RIGHT:
                    cell.setAlignment(Pos.CENTER_RIGHT);
                    break;
                default:
                    cell.setAlignment(Pos.CENTER_LEFT);
                    break;
            }
            return cell;
        }
    }
}
