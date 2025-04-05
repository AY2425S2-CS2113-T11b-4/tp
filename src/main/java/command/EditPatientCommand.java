package command;

import exception.InvalidInputFormatException;
import exception.PatientNotFoundException;
import exception.UnloadedStorageException;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class EditPatientCommand extends Command {

    // details: [0]=nric, [1]=name, [2]=dob, [3]=gender, [4]=address, [5]=phone
    private final String[] details;

    public EditPatientCommand(String[] details) {
        this.details = details;
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws UnloadedStorageException, PatientNotFoundException, InvalidInputFormatException {
        String nric   = details[0];
        String name   = details[1];
        String dob    = details[2];
        String gender = details[3];
        String addr   = details[4];
        String phone  = details[5];

        manager.editPatient(nric, name, dob, gender, addr, phone);
        Ui.showLine();
        System.out.println("Edit-patient command executed.");
        Ui.showLine();
    }
}
