package com.jamith.rmi.controller;

import com.jamith.rmi.dto.UserDTO;
import com.jamith.rmi.dto.UserTableDTO;
import com.jamith.rmi.service.ServiceFactory;
import com.jamith.rmi.service.ServiceHandler;
import com.jamith.rmi.service.UserService;
import com.jamith.rmi.util.Notification;
import com.jamith.rmi.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Jamith Nimantha
 */
public class UserManagementController implements Initializable {

    @FXML
    private Button btnHome;

    @FXML
    private TableView<UserTableDTO> tbl;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private PasswordField txtPassword;

    private UserService userService;

    private UserDTO toBeUpdate;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userService = (UserService) ServiceHandler.getInstance().getService(ServiceFactory.ServiceType.USER);
            loadUserTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUserTable() {
        tbl.getItems().clear();
        tbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("updateButton"));
        tbl.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        try {
            List<UserDTO> userDTOList = userService.getAllUsers();
            List<UserTableDTO> collect = userDTOList.stream().map(userDTO -> {
                UserTableDTO userTableDTO = new UserTableDTO();
                userTableDTO.setTO(userDTO);

                userTableDTO.getUpdateButton().setOnAction(event -> {

                    txtName.setText(userDTO.getFullName());
                    txtEmail.setText(userDTO.getEmail());
                    txtMobile.setText(userDTO.getMobile());
                    btnSave.setText("Update");
                    toBeUpdate = userDTO;
                    tbl.getItems().remove(userTableDTO);
                    tbl.setDisable(true);
                    if (!userDTO.getType().equals("ADMIN")) {
                        txtPassword.setDisable(true);
                    }
                });

                userTableDTO.getDeleteButton().setOnAction(event -> {
                    boolean action = Notification.confirmNotify("Are you Sure?", "This Action cannot be undone", event);
                    if (action)
                        tbl.getItems().remove(userTableDTO);

                });

                return userTableDTO;
            }).collect(Collectors.toList());

            tbl.getItems().addAll(collect);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        tbl.setDisable(false);
        loadUserTable();
        txtMobile.clear();
        txtEmail.clear();
        txtName.clear();
        txtPassword.clear();
        btnSave.setText("Save");
        txtPassword.setDisable(false);
        toBeUpdate = null;
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        ViewLoader.view(
                (Stage) btnHome.getScene().getWindow(),
                this.getClass().getResource("/com/jamith/rmi/view/MainPanel.fxml")
        );
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {

    }
}
