package command;

import exception.AppointmentClashException;
import exception.DuplicatePatientIDException;
import exception.PatientNotFoundException;
import exception.UnloadedStorageException;
import manager.Appointment;
import manager.ManagementSystem;
import manager.Patient;
import miscellaneous.Ui;
import storage.Storage;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeleteAppointmentCommandTest {
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @TempDir
    Path tempDir;
    private ManagementSystem manager;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws UnloadedStorageException {
        storage = new Storage(tempDir.toString());
        ui = new Ui();
        manager = new ManagementSystem(storage.loadPatients(), storage.loadAppointments(manager));
    }

    @Test
    void execute_validNric_expectAppointmentDeleted() throws DuplicatePatientIDException, UnloadedStorageException,
            PatientNotFoundException, AppointmentClashException {
        Patient patient = new Patient("S1234567D", "Billy", "1990-10-01",
                "M", "124 High St", "81234567", new ArrayList<>());
        new AddPatientCommand(patient).execute(manager, ui);

        LocalDateTime dateTime = LocalDateTime.parse("2025-03-25 1900", DATE_TIME_FORMAT);
        Appointment appointment = new Appointment("S1234567D", dateTime, "Checkup");
        new AddAppointmentCommand(appointment).execute(manager, ui);
        new DeleteAppointmentCommand(appointment.getId()).execute(manager, ui);

        assertEquals(0, manager.getAppointments().size(), "Size of appointment does not match");
    }
}
