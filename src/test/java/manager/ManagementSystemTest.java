package manager;

import exception.AppointmentClashException;
import exception.DuplicatePatientIDException;
import exception.PatientNotFoundException;
import exception.UnloadedStorageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ManagementSystemTest {

    private static final String TEST_DIR = "test-data";
    private static final Path TEST_FILE_PATH = Paths.get(TEST_DIR, "patient_data.txt");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");


    @BeforeEach
    void setUp() throws IOException {
        new Storage(TEST_DIR);
        Files.deleteIfExists(TEST_FILE_PATH);
    }

    @Test
    void addPatient_validInput_expectPatientAdded() throws DuplicatePatientIDException, UnloadedStorageException {
        List<Patient> emptyListPatient = new ArrayList<>();
        List<Appointment> emptyListAppoint = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyListPatient, emptyListAppoint);

        Patient patient = new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>());

        manager.addPatient(patient);

        assertEquals(1, manager.getPatients().size());
        assertEquals("John Doe", manager.getPatients().get(0).getName());
    }

    @Test
    void addPatient_duplicateId_expectExceptionThrown() throws DuplicatePatientIDException, UnloadedStorageException {
        List<Patient> existing = new ArrayList<>();
        Patient patient = new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>());
        existing.add(patient);

        ManagementSystem manager = new ManagementSystem(existing, new ArrayList<>());

        Patient duplicate = new Patient("S1234567A", "Jane Smith", "1992-02-02",
                "F", "456 Sample Rd", "90000000", new ArrayList<>());

        assertThrows(DuplicatePatientIDException.class, () -> manager.addPatient(duplicate));
    }

    @Test
    void addPatient_validInput_expectPatientAddedAndSaved()
            throws DuplicatePatientIDException, UnloadedStorageException, IOException {
        List<Patient> emptyListPatient = new ArrayList<>();
        List<Appointment> emptyListAppoint = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyListPatient, emptyListAppoint);

        Patient patient = new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>());
        manager.addPatient(patient);

        assertTrue(Files.exists(TEST_FILE_PATH), "Patient file was not created.");

        List<String> lines = Files.readAllLines(TEST_FILE_PATH);
        assertFalse(lines.isEmpty(), "Patient file is empty.");

        String line = lines.get(0);
        assertTrue(line.contains("John Doe"), "Saved patient name not found in file.");
        assertTrue(line.contains("S1234567A"), "Saved patient NRIC not found in file.");
    }

    @Test
    void deletePatient_existingPatient_patientDeleted() throws UnloadedStorageException {
        List<Patient> patients = new ArrayList<>();
        Patient patient = new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>());
        patients.add(patient);
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        Patient deletedPatient = manager.deletePatient("S1234567A");

        assertNotNull(deletedPatient, "Patient should be deleted");
        assertEquals("John Doe", deletedPatient.getName(), "Deleted patient's name should match");
        assertEquals("S1234567A", deletedPatient.getId(), "Deleted patient's NRIC should match");
        assertEquals(0, manager.getPatients().size(), "Patients list should be empty after deletion");
    }

    @Test
    void deletePatient_nonExistentPatient_patientNotFound() throws UnloadedStorageException {
        List<Patient> patients = new ArrayList<>();
        Patient patient = new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>());
        patients.add(patient);
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        Patient deletedPatient = manager.deletePatient("S9999999X");

        assertNull(deletedPatient, "Patient should not be found and returned as null");
        assertEquals(1, manager.getPatients().size(), "Patients list should remain unchanged");
    }

    @Test
    void deletePatient_emptyList_patientNotFound() throws UnloadedStorageException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        Patient deletedPatient = manager.deletePatient("S1234567A");

        assertNull(deletedPatient, "Patient should not be found in an empty system");
        assertEquals(0, manager.getPatients().size(), "Patients list should remain empty");
    }

    @Test
    void viewPatient_validNric_patientFound() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>()));
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        Patient retrievedPatient = manager.viewPatient("S1234567A");

        assertNotNull(retrievedPatient, "Patient should be found");
        assertEquals("John Doe", retrievedPatient.getName(), "Patient name should match");
        assertEquals("S1234567A", retrievedPatient.getId(), "Patient NRIC should match");
    }

    @Test
    void viewPatient_invalidNric_patientNotFound() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("S1234567A", "John Doe", "1990-01-01",
                "M", "123 Main St", "81234567", new ArrayList<>()));
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        Patient retrievedPatient = manager.viewPatient("S9999999X");

        assertNull(retrievedPatient, "Patient should not be found with this NRIC");
    }

    @Test
    void viewPatient_emptySystem_patientNotFound() {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        Patient retrievedPatient = manager.viewPatient("S1234567A");

        assertNull(retrievedPatient, "Patient should not be found when system is empty");
    }

    @Test
    void addAppointment_validInput_expectAppointmentAdded() throws UnloadedStorageException, PatientNotFoundException,
            AppointmentClashException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-20 1900", DATE_TIME_FORMAT);

        Patient patient = new Patient("S1234567D", "Billy", "1990-10-01",
                "M", "124 High St", "81234567", new ArrayList<>());
        patients.add(patient);
        Appointment appointment = new Appointment("S1234567D", appointmentTime, "Medical Checkup");

        String expectedNric = appointment.getNric();
        manager.addAppointment(appointment);

        assertEquals(1, manager.getAppointments().size(), "Size does not match");
        assertEquals(expectedNric, manager.getAppointments().get(0).getNric(), "NRIC does not match");
        assertEquals(LocalDate.of(2025, 3, 20), manager.getAppointments().get(0).getDate());
        assertEquals(LocalTime.of(19, 0), manager.getAppointments().get(0).getTime());
    }

    @Test
    void addAppointment_clashedAppointments_expectException() throws UnloadedStorageException, PatientNotFoundException,
            AppointmentClashException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        LocalDateTime appointmentTime1 = LocalDateTime.parse("2025-03-20 1900", DATE_TIME_FORMAT);
        LocalDateTime appointmentTime2 = LocalDateTime.parse("2025-03-20 1950", DATE_TIME_FORMAT);

        Patient patient = new Patient("S1234567D", "Billy", "1990-10-01",
                "M", "124 High St", "81234567", new ArrayList<>());
        patients.add(patient);
        Appointment appointment1 = new Appointment("S1234567D", appointmentTime1, "Medical Checkup");
        Appointment appointment2 = new Appointment("S1234567D", appointmentTime2, "X-ray");

        manager.addAppointment(appointment1);
        assertThrows(AppointmentClashException.class, () -> manager.addAppointment(appointment2));
    }

    @Test
    void deleteAppointment_validInput_expectAppointmentDeleted() throws UnloadedStorageException,
            PatientNotFoundException, AppointmentClashException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-20 1900", DATE_TIME_FORMAT);

        Patient patient = new Patient("S1234567D", "Billy", "1990-10-01",
                "M", "124 High St", "81234567", new ArrayList<>());
        patients.add(patient);
        Appointment appointment = new Appointment("S1234567D", appointmentTime, "Medical Checkup");

        String expectedId = appointment.getId();
        manager.addAppointment(appointment);
        Appointment removedAppointment = manager.deleteAppointment(expectedId);

        assertNotNull(removedAppointment, "Deleted appointment should be returned");
        assertEquals(expectedId, appointment.getId(), "Appointment ID does not match");
        assertEquals(0, manager.getAppointments().size(), "Size does not match");
    }

    @Test
    void deleteAppointment_lowerCaseInput_expectAppointmentDeleted() throws UnloadedStorageException,
            PatientNotFoundException, AppointmentClashException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-30 2200", DATE_TIME_FORMAT);

        Patient patient = new Patient("S1234567D", "Billy", "1990-10-01",
                "M", "124 High St", "81234567", new ArrayList<>());
        patients.add(patient);
        Appointment appointment = new Appointment("S1234567D", appointmentTime, "Medical Checkup");

        String expectedId = appointment.getId();
        manager.addAppointment(appointment);
        Appointment removedAppointment = manager.deleteAppointment(expectedId);

        assertNotNull(removedAppointment, "Deleted appointment should be returned");
        assertEquals(expectedId, appointment.getId(), "Appointment ID does not match");
        assertEquals(0, manager.getAppointments().size(), "Size does not match");
    }

    @Test
    void deleteAppointment_nonExistentId_expectNullReturned() throws UnloadedStorageException,
            PatientNotFoundException, AppointmentClashException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-25 2100", DATE_TIME_FORMAT);

        Patient patient = new Patient("S1234567D", "Billy", "1990-10-01",
                "M", "124 High St", "81234567", new ArrayList<>());
        patients.add(patient);
        Appointment appointment = new Appointment("S1234567D", appointmentTime, "Medical Checkup");

        manager.addAppointment(appointment);
        Appointment removedAppointment = manager.deleteAppointment("A999");

        assertEquals(1, manager.getAppointments().size(), "Size does not match");
        assertNull(removedAppointment, "Should return null");
    }

    @Test
    void sortAppointmentsByDateTime_sortByDateTime_appointmentsSortedByDateTime() {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>(), new ArrayList<>());
        List<Appointment> appointments = new ArrayList<>();

        LocalDateTime appointmentTime1 = LocalDateTime.parse("2025-03-25 1900", DATE_TIME_FORMAT);
        LocalDateTime appointmentTime2 = LocalDateTime.parse("2025-03-24 1200", DATE_TIME_FORMAT);
        LocalDateTime appointmentTime3 = LocalDateTime.parse("2025-03-25 1000", DATE_TIME_FORMAT);

        Appointment appointment1 = new Appointment("S1234567D", appointmentTime1, "Checkup");
        appointments.add(appointment1);

        Appointment appointment2 = new Appointment("S4567890D", appointmentTime2, "CT scan");
        appointments.add(appointment2);

        Appointment appointment3 = new Appointment("S7891234D", appointmentTime3, "Consultation");
        appointments.add(appointment3);

        manager.sortAppointmentsByDateTime(appointments);

        assertEquals("CT scan", appointments.get(0).getDescription());
        assertEquals("Consultation", appointments.get(1).getDescription());
        assertEquals("Checkup", appointments.get(2).getDescription());
    }

    @Test
    void sortAppointmentsById_sortById_appointmentsSortedById() {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>(), new ArrayList<>());
        List<Appointment> appointments = new ArrayList<>();

        LocalDateTime appointmentTime1 = LocalDateTime.parse("2025-03-25 1900", DATE_TIME_FORMAT);
        LocalDateTime appointmentTime2 = LocalDateTime.parse("2025-03-24 1200", DATE_TIME_FORMAT);
        LocalDateTime appointmentTime3 = LocalDateTime.parse("2025-03-25 1000", DATE_TIME_FORMAT);

        Appointment appointment1 = new Appointment("S1234567D", appointmentTime1, "Checkup");
        appointments.add(appointment1);

        Appointment appointment2 = new Appointment("S4567890D", appointmentTime2, "CT scan");
        appointments.add(appointment2);

        Appointment appointment3 = new Appointment("S7891234D", appointmentTime3, "Consultation");
        appointments.add(appointment3);

        manager.sortAppointmentsByDateTime(appointments);
        manager.sortAppointmentsById(appointments);

        assertEquals("Checkup", appointments.get(0).getDescription());
        assertEquals("CT scan", appointments.get(1).getDescription());
        assertEquals("Consultation", appointments.get(2).getDescription());
    }

    //@@author dylancmznus
    @Test
    void markAppointment_validInput_expectAppointmentMarked() throws DuplicatePatientIDException,
            UnloadedStorageException, PatientNotFoundException, AppointmentClashException {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>(), new ArrayList<>());

        Patient patient = new Patient("S9876543Z", "John Doe", "1990-01-01",
                "M", "123 Street", "12345678", new ArrayList<>());
        manager.addPatient(patient);

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-04-10 1500", DATE_TIME_FORMAT);
        Appointment appointment = new Appointment("S9876543Z", appointmentTime, "Dental Checkup");

        manager.addAppointment(appointment);
        manager.markAppointment(appointment.getId());

        assertTrue(manager.getAppointments().get(0).isDone(), "Appointment should be marked");
    }

    //@@author dylancmznus
    @Test
    void unmarkAppointment_validInput_expectAppointmentUnmarked() throws DuplicatePatientIDException,
            UnloadedStorageException, PatientNotFoundException, AppointmentClashException {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>(), new ArrayList<>());

        Patient patient = new Patient("S8765432Y", "John Doe", "1990-01-01",
                "M", "123 Street", "12345678", new ArrayList<>());
        manager.addPatient(patient);

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-05-15 1030", DATE_TIME_FORMAT);
        Appointment appointment = new Appointment("S8765432Y", appointmentTime, "Eye Examination");

        manager.addAppointment(appointment);
        manager.markAppointment(appointment.getId());
        manager.unmarkAppointment(appointment.getId());

        assertFalse(manager.getAppointments().get(0).isDone(), "Appointment should be unmarked");
    }

    //@@author dylancmznus
    @Test
    void findAppointment_existingAppointment_expectAppointmentFound() throws DuplicatePatientIDException,
            UnloadedStorageException, PatientNotFoundException, AppointmentClashException {
        ManagementSystem manager = new ManagementSystem(new ArrayList<>(), new ArrayList<>());

        Patient patient = new Patient("S7654321X", "John Doe", "1990-01-01",
                "M", "123 Street", "12345678", new ArrayList<>());
        manager.addPatient(patient);

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-06-20 0900", DATE_TIME_FORMAT);
        Appointment appointment = new Appointment("S7654321X", appointmentTime, "General Consultation");

        manager.addAppointment(appointment);
        List<Appointment> foundAppointments = manager.findAppointmentsByNric(appointment.getNric());

        assertNotNull(foundAppointments, "Appointment should be found");
        assertEquals(appointment.getId(), foundAppointments.get(0).getId(), "Appointment ID should match");
    }

    //@@author dylancmznus
    @Test
    void findAppointment_nonExistentAppointment_expectNullReturned() {
        List<Patient> emptyListPatient = new ArrayList<>();
        List<Appointment> emptyListAppoint = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(emptyListPatient, emptyListAppoint);

        List<Appointment> foundAppointments = manager.findAppointmentsByNric("A999");

        assertTrue(foundAppointments.isEmpty(), "Non-existent appointment should return empty list");
    }


    //@@author jyukuan
    @Test
    void storeMedicalHistory_storeMedHistoryOnNewPatient_expectOneNewPatientWithMedHistory()
            throws UnloadedStorageException, PatientNotFoundException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem mhm = new ManagementSystem(patients, new ArrayList<>());

        LocalDateTime appointmentTime = LocalDateTime.parse("2025-03-20 1900", DATE_TIME_FORMAT);

        Patient patient = new Patient("S1234567A", "John Doe", "1990-10-01",
                "M", "124 High St", "81234567", new ArrayList<>());
        patients.add(patient);

        mhm.storeMedicalHistory("S1234567A", "Diabetes, Hypertension");

        assertEquals(1, patients.size(), "There should be one patient stored");

        Patient storedPatient = patients.get(0);
        assertEquals("John Doe", storedPatient.getName(), "Patient name should match");
        assertEquals("S1234567A", storedPatient.getId(), "Patient NRIC should match");

        List<String> history = storedPatient.getMedicalHistory();
        assertEquals(2, history.size(), "Medical history should contain 2 entries");
        assertTrue(history.contains("Diabetes"), "Medical history should contain 'Diabetes'");
        assertTrue(history.contains("Hypertension"), "Medical history should contain 'Hypertension'");
    }

    @Test
    void editPatientHistory_oldEntryNotFound_expectNoChange() throws UnloadedStorageException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());

        List<String> history = new ArrayList<>(List.of("Cold", "Migraine"));
        Patient patient = new Patient("F8888888Q", "Ellen", "1970-12-12", "F", "99 Peace Ave", "85556666", history);
        patients.add(patient);

        manager.editPatientHistory("F8888888Q", "Cancer", "Diabetes");

        List<String> updatedHistory = patient.getMedicalHistory();

        assertEquals(2, updatedHistory.size(), "Unexpected change in history size");
        assertFalse(updatedHistory.contains("Diabetes"), "Incorrectly added new history");
        assertTrue(updatedHistory.contains("Cold"), "Valid entry unexpectedly removed");
        assertTrue(updatedHistory.contains("Migraine"), "Valid entry unexpectedly removed");
    }

    @Test
    void editPatientHistory_validHistory_expectUpdated() throws UnloadedStorageException {
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());
        List<String> history = new ArrayList<>(List.of("High BP", "Migraine"));
        Patient patient = new Patient("F1234567X", "Carol", "1975-03-15", "F", "Blk 999", "83334444", history);
        patients.add(patient);

        manager.editPatientHistory("F1234567X", "High BP", "Hypertension");

        List<String> updatedHistory = patient.getMedicalHistory();
        assertTrue(updatedHistory.contains("Hypertension"), "Replacement failed");
        assertFalse(updatedHistory.contains("High BP"), "Old entry not removed");
    }

    @Test
    void editPatientHistory_emptyNewHistory_expectAssertionError() {
        // Setup
        List<Patient> patients = new ArrayList<>();
        ManagementSystem manager = new ManagementSystem(patients, new ArrayList<>());
        List<String> history = new ArrayList<>(List.of("Headache"));
        Patient patient = new Patient("T7654321B", "Sarah", "1992-03-03",
                "F", "88 Health Ave", "81231234", history);
        patients.add(patient);

        // Verify assertion
        assertThrows(AssertionError.class,
                () -> manager.editPatientHistory("T7654321B", "Headache", ""));
    }

}
