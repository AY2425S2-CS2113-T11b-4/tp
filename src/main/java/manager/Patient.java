package manager;

import exception.InvalidInputFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String id;
    private String name;
    private LocalDate dob;
    private String contactInfo;
    private String gender;
    private String address;
    private final List<String> medicalHistory;
    private final List<Appointment> appointments;

    public Patient(String id, String name, String dobStr, String gender, String address,
                   String contactInfo, List<String> medicalHistory) throws InvalidInputFormatException {
        assert id != null && !id.isBlank() : "Patient ID cannot be null or blank";
        assert name != null && !name.isBlank() : "Patient name cannot be null or blank";
        assert dobStr != null : "Date of birth cannot be null";
        assert gender != null : "Gender cannot be null";
        assert address != null : "Address cannot be null";
        assert contactInfo != null : "Contact info cannot be null";
        assert medicalHistory != null : "Medical history list cannot be null";
        
        this.id = id;
        this.name = name;
        this.dob = parseAndValidateDob(dobStr);
        this.gender = gender;
        this.address = address;
        this.contactInfo = contactInfo;
        this.medicalHistory = new ArrayList<>(medicalHistory);
        this.appointments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        assert appointment != null : "Appointment cannot be null";
        assert appointment.getNric().equals(this.id) : "Appointment NRIC must match patient ID";
        appointments.add(appointment);
    }

    private LocalDate parseAndValidateDob(String dobStr) throws InvalidInputFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate dob = LocalDate.parse(dobStr, formatter);
            if (dob.isAfter(LocalDate.now())) {
                throw new InvalidInputFormatException("Date of birth must be before the current date.");
            }
            return dob;
        } catch (DateTimeParseException e) {
            throw new InvalidInputFormatException("Invalid date format. Use dd-MM-yyyy.");
        }
    }

    public void deleteAppointment(String apptId) {
        assert apptId != null && !apptId.isBlank() : "Appointment ID cannot be null or blank";
        for (Appointment appt : appointments) {
            if (appt.getId().equals(apptId)) {
                appointments.remove(appt);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String formattedMedicalHistory;
        if (medicalHistory.isEmpty()) {
            formattedMedicalHistory = "None";
        } else {
            formattedMedicalHistory = String.join(", ", medicalHistory);
        }


        String result = String.format(
                "Patient NRIC: %s\n"
                        + "Name: %s\n"
                        + "Date of Birth: %s\n"
                        + "Gender: %s\n"
                        + "Address: %s\n"
                        + "Contact: %s\n"
                        + "Medical History: %s",
                id, name, dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), gender, address, contactInfo, formattedMedicalHistory);

        if (appointments.isEmpty()) {
            result += "\nAppointments: None";
        } else {
            result += "\nAppointments:";
            for (Appointment appt : appointments) {
                result += String.format(
                        "\n- [%s][%s]: %s (%s)",
                        appt.getId(),
                        appt.getStatusIcon(),
                        appt.getDateTime().format(Appointment.OUTPUT_FORMAT),
                        appt.getDescription());
            }
        }
        return result;
    }

    public String toStringForListView() {
        String result = String.format(
                "Patient NRIC: %s\n   "
                        + "Name: %s\n   "
                        + "Date of Birth: %s\n   "
                        + "Gender: %s\n   "
                        + "Address: %s\n   "
                        + "Contact: %s",
                id, name, dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), gender, address, contactInfo);

        if (medicalHistory.isEmpty()) {
            result += "\n   Medical History: None";
        } else {
            result += "\n   Medical History:";
            for (String h : medicalHistory) {
                result += "\n   - " + h;
            }
        }

        if (appointments.isEmpty()) {
            result += "\n   Appointments: None";
        } else {
            result += "\n   Appointments:";
            for (Appointment appt : appointments) {
                result += String.format(
                        "\n   - [%s][%s]: %s (%s)",
                        appt.getId(),
                        appt.getStatusIcon(),
                        appt.getDateTime().format(Appointment.OUTPUT_FORMAT),
                        appt.getDescription());
            }
        }
        return result;
    }

    public String toFileFormat() {
        String history = String.join(", ", this.medicalHistory);
        return this.id + "|" + this.name + "|" + dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "|" + this.gender + "|"
                + this.address + "|" + this.contactInfo + "|" + history;
    }
}
