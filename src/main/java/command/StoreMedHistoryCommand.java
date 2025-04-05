package command;

import exception.PatientNotFoundException;
import exception.UnloadedStorageException;
import manager.ManagementSystem;
import miscellaneous.Ui;

public class StoreMedHistoryCommand extends Command {
    private String nric;
    private String medHistory;

    public StoreMedHistoryCommand(String[] details) {
        this.nric = details[0];
        this.medHistory = details[1];
    }

    @Override
    public void execute(ManagementSystem manager, Ui ui) throws UnloadedStorageException, PatientNotFoundException {
        manager.storeMedicalHistory(nric, medHistory);
    }
}
