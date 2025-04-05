package command;

import exception.*;
import manager.Appointment;
import manager.ManagementSystem;
import manager.Patient;
import miscellaneous.Ui;
import storage.Storage;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ListAppointmentCommandTest {
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
    void execute_always_requestsAppointmentsFromManager() throws DuplicatePatientIDException, UnloadedStorageException,
            PatientNotFoundException, AppointmentClashException, InvalidInputFormatException {
        LocalDateTime dateTime1 = LocalDateTime.parse("2025-03-25 1900", DATE_TIME_FORMAT);
        LocalDateTime dateTime2 = LocalDateTime.parse("2025-03-28 2000", DATE_TIME_FORMAT);
        LocalDateTime dateTime3 = LocalDateTime.parse("2025-03-23 1200", DATE_TIME_FORMAT);

        List<Patient> patients = List.of(
                new Patient("S1234567D", "Billy", "01-10-1990",
                        "M", "124 High St", "81234567", new ArrayList<>()),
                new Patient("S2345678D", "James" , "31-12-1980",
                        "M", "133 Main St", "81229312", new ArrayList<>()),
                new Patient("S3456789D", "William" , "31-08-1970",
                        "M", "17 Cornelia St", "81009214", new ArrayList<>())
        );
        manager.addPatient(patients.get(0));
        manager.addPatient(patients.get(1));
        manager.addPatient(patients.get(2));

        List<Appointment> appointments = List.of(
                new Appointment("S1234567D", dateTime1 , "Checkup"),
                new Appointment("S2345678D", dateTime2 , "CT scan"),
                new Appointment("S3456789D", dateTime3 , "Consultation")
        );
        manager.addAppointment(appointments.get(0));
        manager.addAppointment(appointments.get(1));
        manager.addAppointment(appointments.get(2));

        new ListAppointmentCommand().execute(manager, ui);

        assertEquals(3, manager.getAppointments().size(), "Size of appointment does not match");
    }

}
