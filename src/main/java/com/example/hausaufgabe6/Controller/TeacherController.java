package com.example.hausaufgabe6.Controller;

import com.example.hausaufgabe6.Exceptions.NullException;
import com.example.hausaufgabe6.Model.Course;
import com.example.hausaufgabe6.Model.Student;
import com.example.hausaufgabe6.Model.Teacher;
import com.example.hausaufgabe6.Repository.CourseRepository;
import com.example.hausaufgabe6.Repository.StudentRepository;
import com.example.hausaufgabe6.Repository.TeacherRepository;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class TeacherController implements Initializable {

    @FXML
    private Button closeButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField password;

    @FXML
    BorderPane borderPane;

    @FXML
    private Long loggedInTeacherId;

    @FXML
    private Text titleCenter;

    @FXML
    private Text teacherName;

    @FXML
    private Text titleLeft1;
    @FXML
    private Text titleLeft2;

    @FXML
    Button backToStartButton;

    @FXML
    private StudentRepository studentsRepo;

    @FXML
    private TeacherRepository teachersRepo;

    @FXML
    private CourseRepository coursesRepo;

    @FXML
    Button showButton;

    @FXML
    ChoiceBox<String> chooseCourse;

    @FXML
    Course currentCourse;

    @FXML
    TableView<Student> studentsEnrolledTable;

    @FXML
    Button showEnrolledStudents;

    @FXML
    VBox vbox;

    @FXML
    Button refreshButton;

    @FXML
    Label statusLabel;

    @FXML
    Label loginErrorLabel;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.studentsEnrolledTable.setVisible(false);

        // button Login
        closeButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/TeacherLoginView.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert root != null;
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("User Log-in");
            stage.show();
        });
        // button Show Enrolled Students
        showEnrolledStudents.setOnAction(actionEvent -> {
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EnrolledStudentsView.fxml"));
            loader.setController(this);
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            borderPane.setCenter(parent);
        });
        // ChoiceBox
        chooseCourse.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    try {
                        Teacher loggedInTeacher = this.teachersRepo.findOne(this.loggedInTeacherId);
                        currentCourse = loggedInTeacher.getCourses().get(new_val.intValue());
                    } catch (SQLException | NullException e) {
                        e.printStackTrace();
                    }
                });
        // button TableView visible
        showButton.setOnAction(e ->
        {
            this.statusLabel.setVisible(false);
            if (currentCourse != null) {
                try {
                    ObservableList<Student> students = FXCollections.observableArrayList(this.coursesRepo.findOne(this.currentCourse.getCourseId()).getStudentsEnrolled());
                    this.studentsEnrolledTable.setItems(students);
                    TableColumn<Student, String> firstNameCol = new TableColumn<>("First Name");
                    firstNameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getFirstName()));
                    TableColumn<Student, String> lastNameCol = new TableColumn<>("Last Name");
                    lastNameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getLastName()));
                    TableColumn<Student, String> idCol = new TableColumn<>("Student id");
                    idCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getStudentId())));
                    TableColumn<Student, String> creditsCol = new TableColumn<>("Credits");
                    creditsCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getTotalCredits())));
                    this.studentsEnrolledTable.getColumns().setAll(firstNameCol, lastNameCol, idCol, creditsCol);
                    this.studentsEnrolledTable.setFixedCellSize(25);
                    this.studentsEnrolledTable.prefHeightProperty().bind(this.studentsEnrolledTable.fixedCellSizeProperty().multiply(Bindings.size(this.studentsEnrolledTable.getItems()).add(1.01)));
                    this.studentsEnrolledTable.minHeightProperty().bind(this.studentsEnrolledTable.prefHeightProperty());
                    this.studentsEnrolledTable.maxHeightProperty().bind(this.studentsEnrolledTable.prefHeightProperty());
                    this.studentsEnrolledTable.setVisible(true);
                } catch (SQLException | NullException ex) {
                    ex.printStackTrace();
                }
            } else {
                this.statusLabel.setText("No course chosen :(");
                this.statusLabel.setVisible(true);
            }
        });
        // button resets the TableView
        refreshButton.setOnAction(e ->
        {
            System.out.println(currentCourse);
            if (currentCourse != null) {
                try {
                    ObservableList<Student> students = FXCollections.observableArrayList(this.coursesRepo.findOne(this.currentCourse.getCourseId()).getStudentsEnrolled());
                    this.studentsEnrolledTable.setItems(students);
                    TableColumn<Student, String> firstNameCol = new TableColumn<>("First Name");
                    firstNameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getFirstName()));
                    TableColumn<Student, String> lastNameCol = new TableColumn<>("Last Name");
                    lastNameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getLastName()));
                    TableColumn<Student, String> idCol = new TableColumn<>("Student id");
                    idCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getStudentId())));
                    TableColumn<Student, String> creditsCol = new TableColumn<>("Credits");
                    creditsCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getTotalCredits())));
                    this.studentsEnrolledTable.getColumns().setAll(firstNameCol, lastNameCol, idCol, creditsCol);
                    this.studentsEnrolledTable.setFixedCellSize(25);
                    this.studentsEnrolledTable.prefHeightProperty().bind(this.studentsEnrolledTable.fixedCellSizeProperty().multiply(Bindings.size(this.studentsEnrolledTable.getItems()).add(1.01)));
                    this.studentsEnrolledTable.minHeightProperty().bind(this.studentsEnrolledTable.prefHeightProperty());
                    this.studentsEnrolledTable.maxHeightProperty().bind(this.studentsEnrolledTable.prefHeightProperty());
                    this.studentsEnrolledTable.setVisible(true);
                } catch (SQLException | NullException ex) {
                    statusLabel.setText("Courses has been refreshed! Choose a course!");
                }
            }
        });
        this.chooseCourse.setTooltip(new Tooltip("Select the course:"));
        // stores the current teacherId
        if (loggedInTeacherId != null) {
            try {
                this.teacherName.setText(this.teachersRepo.findOne(loggedInTeacherId).getFirstName());
                this.chooseCourse.setItems(FXCollections.observableArrayList(this.teachersRepo.findOne(this.loggedInTeacherId).getCourses()
                        .stream()
                        .map(Course::toShow)
                        .collect(Collectors.toList())));
            } catch (SQLException | NullException e) {
                e.printStackTrace();
            }
        }
    }

    public TeacherController() throws SQLException {
        this.studentsRepo = new StudentRepository();
        this.teachersRepo = new TeacherRepository();
        this.coursesRepo = new CourseRepository();
        this.studentsRepo = new StudentRepository();
        this.coursesRepo = new CourseRepository();
        this.closeButton = new Button();
        this.titleCenter = new Text();
        this.titleCenter.setText("Teacher Log-in");
        this.teacherName = new Text();
        this.chooseCourse = new ChoiceBox<>();
        this.showButton = new Button();
        this.currentCourse = null;
        this.studentsEnrolledTable = new TableView<>();
        this.studentsEnrolledTable.setVisible(false);
        this.showEnrolledStudents = new Button();
        this.refreshButton = new Button();
        this.statusLabel = new Label();
        this.loginErrorLabel = new Label();
    }

    /**
     * redirects to the Main Panel
     */
    @FXML
    private void backToStart() throws IOException {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainPanelView.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Start Page");
        stage.show();
    }

    /**
     * redirects to the Student Account
     *
     * @throws SQLException  if connection to database could not succeed
     * @throws NullException if input parameter id is NULL
     */
    @FXML
    public void login() throws SQLException, NullException, IOException {
        Teacher loggedInTeacher = this.validTeacher(firstName.getText(), lastName.getText(), password.getText());
        if (loggedInTeacher != null) {
            loggedInTeacherId = loggedInTeacher.getTeacherId();
            this.chooseCourse.setItems(FXCollections.observableArrayList(this.teachersRepo.findOne(this.loggedInTeacherId).getCourses()
                    .stream()
                    .map(Course::toShow)
                    .collect(Collectors.toList())));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TeacherAccount.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Teacher Page");
            stage.show();
        } else {
            firstName.requestFocus();
            lastName.requestFocus();
            password.requestFocus();
            this.loginErrorLabel.setText("Log-in Error! Incorrect name or password!");
        }
    }

    /**
     * Validates the input teacher credentials
     *
     * @param firstName from user introduced first name
     * @param lastName  from user introduced last name
     * @param password  from user introduced password
     * @return the logged in Teacher if the input credentials are correct, else null
     * @throws SQLException  if connection to database could not succeed
     * @throws NullException if input parameter id is NULL
     */
    private Teacher validTeacher(String firstName, String lastName, String password) throws SQLException, NullException {
        Teacher teacher = this.teachersRepo.findOne(Long.valueOf(password));
        if (teacher != null && teacher.getFirstName().equals(firstName) && teacher.getLastName().equals(lastName)) {
            return teacher;
        }
        return null;
    }
}