package command;

import exception.PatientNotFoundException;
import exception.UnloadedStorageException;
import manager.ManagementSystem;
import miscellaneous.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EditPatientCommand extends Command {

    // details: [0]=nric, [1]=name, [2]=dob, [3]=gender, [4]=address, [5]=phone
    private final String[] details;

    public EditPatientCommand(String[] details) {
        this.details = details;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui)
            throws UnloadedStorageException, PatientNotFoundException {

        String nric   = details[0];
        String name   = details[1];
        String dob    = details[2];
        String gender = details[3];
        String addr   = details[4];
        String phone  = details[5];

        if (dob != null && !dob.isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate.parse(dob, formatter);
            } catch (DateTimeParseException e) {
                ui.showError("Invalid date format for Date of Birth! Please use yyyy-MM-dd (e.g., 1990-05-12).");
                return;
            }
        }

        manager.editPatient(nric, name, dob, gender, addr, phone);
        Ui.showLine();
        System.out.println("Edit-patient command executed.");
        Ui.showLine();
    }
}
