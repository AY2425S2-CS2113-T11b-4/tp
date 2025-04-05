# User Guide

## Introduction

ClinicEase is an application designed to assist doctors in managing patient records and appointments efficiently. It allows the user to add, edit, view, and delete patient information and medical history, as well as schedule and track appointments with ease. With its intuitive command-based interface, ClinicEase ensures smooth and reliable clinic operations.

---
## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features-)
    - [Adding a new patient: `add-patient`](#adding-a-new-patient--add-patient)
    - [Viewing patient details: `view-patient`](#viewing-patient-details-view-patient)
    - [Listing all patients: `list-patient`](#listing-all-patients-list-patient)
    - [Editing a specified patient: `edit-patient`](#editing-a-specified-patient-edit-patient)
    - [Adding an appointment: `add-appointment`](#adding-an-appointment-add-appointment)
    - [Deleting an appointment: `delete-appointment`](#deleting-an-appointment-delete-appointment)
    - [Sorting appointments: `sort-appointment`](#sorting-appointments-sort-appointment)
    - [Marking an appointment as done: `mark-appointment`](#marking-an-appointment-as-done-mark-appointment)
    - [Unmarking a completed appointment: `unmark-appointment`](#unmarking-a-completed-appointment-unmark-appointment)
    - [Finding a patient's appointments: `find-appointment`](#finding-a-patients-appointments-find-appointment)
    - [Managing Prescriptions](#managing-prescriptions)
        - [Adding a prescription: `add-prescription`](#adding-a-prescription-add-prescription)
        - [Viewing all prescriptions: `view-all-prescriptions`](#viewing-all-prescriptions-view-all-prescriptions)
        - [Viewing specific prescription: `view-prescription`](#viewing-specific-prescription-view-prescription)
    - [Adding medical history: `store-history`](#adding-medical-history-store-history)
    - [Viewing medical history: `view-history`](#viewing-medical-history-view-history)
    - [Editing medical history: `edit-history`](#editing-medical-history-edit-history)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)

---
## Quick Start

1. Ensure that you have Java 17 or above installed. <br> 
   **Mac users:** Ensure you have the precise JDK version prescribed 
   [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. Download the latest version of `ClinicEase` [here](https://github.com/AY2425S2-CS2113-T11b-4/tp/releases).   
3. Copy the file to the folder you want to use as the home folder for ClinicEase.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the 
   `java -jar ClinicEase.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. <br>
   Some example commands you can try:
   - `add-patient n/John ic/S1234567D dob/2003-04-06 g/M p/91218188 a/123 Main St`: <br>
     Adds a patient named `John` to the patient list.
   - `delete-patient S1234567D`: Deletes the patient with NRIC `S1234567D` in the patient list. 
   - `bye`: Exits the app.
6. Refer to the Features below for details of each command.

---
## Features 
Here are some feature that the product provides:

* `add-patient`: adds a patient to the system
* ``
> [!NOTE]
> - Words in `UPPER_CASE` represent parameters that must be provided by the user. <br>
    e.g. in `view-patient NRIC`, `NRIC` is a parameter which can be used as `view-patient S1234567D`.
> - Items in square brackets are optional. <br>
    e.g. `ic/NRIC [n/NAME] [dob/BIRTHDATE]` can be used as `ic/S1234567D n/John` or as `ic/S1234567D dob/2002-06-07`.
> - Every parameter must be supplied by the user. <br>
    e.g. if the command specifies `add-appointment ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`, the user
    must fill in all parameters for the input to be valid.
> - Parameters could be entered in any order. <br>
    e.g. if the command recommends `ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`, the user
    can also not follow this exact sequence for the input to be valid.
> - Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored. <br>
    e.g. if the command specifies `list-patient 12345`, it will be interpreted as `list-patient`.
> - Command words are **case-insensitive**. <br>
    e.g. `liST-paTIEnt` will be interpreted as `list-patient`.

<br>

### Adding a new patient : `add-patient`
Adds a new patient to the system with their credentials.

Format: `add-patient n/NAME ic/NRIC dob/BIRTHDATE g/GENDER p/PHONE a/ADDRESS h/MEDICAL_HISTORY`

* The `NRIC` must be unique to the existing ones in the system.

Example of usage: 

`add-patient n/John Doe ic/S1234567D dob/12-12-1999 g/M p/98765432 a/123 Main Street h/Diabetes, Hypertension`

Expected output:

<pre>---------------------------------------------------------------------------------------------------- 
Patient added successfully: John Doe 
---------------------------------------------------------------------------------------------------- </pre>
<br>

### Deleting a patient : `delete-patient`
Deletes an existing patient in the system.

Format: `delete-patient NRIC`

* The `NRIC` must be of a patient existing in the system.

Example of usage:

`delete-patient S1234567D`

Expected output:

<pre>---------------------------------------------------------------------------------------------------- 
Patient deleted successfully: John Doe
---------------------------------------------------------------------------------------------------- </pre>
<br>

### Viewing patient details: `view-patient`
Displays the details of a specific patient.

Format: `view-patient NRIC`

* The `NRIC` must be of a patient existing in the system.

Example of usage:

`view-patient S1234567D`

Expected Output:

<pre>------------------------------------------Patient Details------------------------------------------
Patient NRIC: S1234567D
Name: John Doe
Date of Birth: 12-12-1999
Gender: M
Address: 123 Main Street
Contact: 98765432
Medical History: Diabetes, Hypertension
Appointments: None
----------------------------------------------------------------------------------------------------</pre>
<br>

### Listing all patients: `list-patient`
Displays a list of all registered patients in the system with details provided.

Format: `list-patient`

Example of usage:

`list-patient`

Expected Output:

<pre>------------------------------------------Patient Details------------------------------------------
1. Patient NRIC: S1234567D
   Name: John Doe
   Date of Birth: 12-12-1999
   Gender: M
   Address: 123 Main Street
   Contact: 98765432
   Medical History:
   - Diabetes
   - Hypertension
   Appointments: None
----------------------------------------------------------------------------------------------------
2. Patient NRIC: S8765432F
   Name: Jane Donna
   Date of Birth: 12-05-2000
   Gender: F
   Address: 546 Main Street
   Contact: 91209310
   Medical History:
   - Cough
   Appointments: None
----------------------------------------------------------------------------------------------------</pre>
<br>

### Editing a specified patient: `edit-patient`
Displays a list of all registered patients in the system with details provided.

Format: `edit-patient ic/NRIC ATTRIBUTE`

* The `NRIC` must be of a patient existing in the system.
* The `ATTRIBUTE` can possibly be between `n/NAME`, `dob/BIRTHDATE`, `g/GENDER`, `a/ADDRESS`, or `p/PHONE` 

Example of usage:

`edit-patient ic/S1234567D p/91238989` or `edit-patient ic/S1234567D dob/17-08-1945`

Expected output:

<pre>Patient with NRIC S1234567D updated successfully. 
----------------------------------------------------------------------------------------------------.
Edit-patient command executed.
---------------------------------------------------------------------------------------------------- </pre>
<br>

---

### Adding an appointment: `add-appointment`
Adds a new appointment to the list of appointment.

Format: `add-appointment ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`

* The patient with the specified `NRIC` **must** exist in the system.
* `DATE` format: `yyyy-MM-dd`, where `yyyy` is year, `MM` is month,
  `dd` is day (e.g., `2025-03-31`).  
* `TIME` format: `HHmm` in 24-hour format (e.g., `1430` for 2:30 PM).
* `DATE` and `TIME` **must not** be **before current date/time**.

Example of usage: 

`add-appointment ic/S1234567D dt/2025-03-31 t/1200 dsc/Annual checkup`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Appointment added for NRIC: S1234567D on 2025-03-31 at 12:00 PM.
Now you have 1 appointment(s) in the list.
----------------------------------------------------------------------------------------------------</pre>
<br>

### Deleting an appointment: `delete-appointment`
Deletes a specified appointment from the appointment list.

Format: `delete-appointment APPOINTMENT_ID`

* The `APPOINTMENT_ID` refers to the unique identifier assigned by the program (e.g., "A1XX") to an appointment.
* The `APPOINTMENT_ID` can be found in the displayed appointment list when using the list-appointment command.

Example of usage:

`delete-appointment A100`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Appointment A100 is deleted successfully.
Now you have 0 appointment(s) in the list.
----------------------------------------------------------------------------------------------------</pre>
<br>

### Sorting appointments: `sort-appointment`
Sorts the appointments in the appointment list.

Format: `sort-appointment byDate` or `sort-appointment byId`

* The `sort-appointment byDate` sorts the appointments by date and time in **ascending order**. 
* The `sort-appointment byId` sorts the appointments by `APPOINTMENT_ID` in **ascending order**.

Example of usage:

* `sort-appointment byDate`
* `sort-appointment byId`

Expected output: 

<pre>-------------------------------------------Appointments---------------------------------------------
1. [A101][ ] - S1234567D - 2025-03-31 12:00 PM - Annual checkup
2. [A102][ ] - S1234567D - 2025-04-30 2:00 PM - Annual checkup
----------------------------------------------------------------------------------------------------</pre>
<br>

### Marking an appointment as done: `mark-appointment`
Marks a specified appointment as done.

Format: `mark-appointment APPOINTMENT_ID`

* The `APPOINTMENT_ID` refers to the unique identifier assigned by the system to an appointment.
* A completed appointment will be marked accordingly in the system.

Example of usage:

`mark-appointment A100`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Appointment A101 is marked successfully.
----------------------------------------------------------------------------------------------------</pre>
<br>

### Unmarking a completed appointment: `unmark-appointment`
Unmarks a completed appointment, setting it back to pending.

Format: `unmark-appointment APPOINTMENT_ID`

* The `APPOINTMENT_ID` must belong to an appointment that has been marked as completed.

Example of usage:

`unmark-appointment A100`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Appointment A101 is unmarked successfully.
----------------------------------------------------------------------------------------------------</pre>
<br>

### Finding a patient's appointments: `find-appointment`
Searches for appointments based on the patient's NRIC.

Format: `find-appointment NRIC`

* The `NRIC` must match a registered patient’s NRIC in the system.
* All appointments associated with the specified NRIC will be displayed.

Example of usage:

`find-appointment S1234567D`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
 Appointments found for NRIC: S1234567D
 - [A101][ ] - S1234567D - 2025-03-31 12:00 PM - Annual checkup
 - [A102][ ] - S1234567D - 2025-04-30 2:00 PM - Annual checkup
----------------------------------------------------------------------------------------------------</pre>
<br>

### Adding a prescription: `add-prescription`

Adds a new prescription for a patient.

Format: `add-prescription ic/PATIENT_ID s/SYMPTOMS m/MEDICINES [nt/NOTES]`

* `PATIENT_ID` must be a valid patient ID in the system
* `SYMPTOMS` is a comma-separated list of symptoms
* `MEDICINES` is a comma-separated list of prescribed medications
* `NOTES` is optional and can contain special instructions

Example of usage:

`add-prescription ic/S9876543B s/Fever, Cough m/Paracetamol, Cough syrup nt/Take after meals`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Successfully added prescription:
Prescription [S1234567D-1] (2025-04-05 18:52)
Patient ID: S1234567D
Symptoms: 
- Fever
- Cough
Medicines: 
- Paracetamol
- Cough syrup
Notes: Take after meals

Prescription has been generated.
View the prescription for the patient with ID: S1234567D
and prescription ID: S1234567D-1
----------------------------------------------------------------------------------------------------</pre>
<br>

### Viewing all prescriptions: `view-all-prescriptions`

Shows all prescriptions for a specific patient.

Format: `view-all-prescriptions NRIC`

`NRIC` must be an existing patient in the system

Example of Usage:

`view-all-prescriptions S9876543B`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Prescriptions for patient John Doe (S1234567D):

Prescription ID: S1234567D-1
Date: 2025-04-05 18:52
Symptoms:
- Fever
- Cough
Medicines:
- Paracetamol
- Cough syrup
Notes: Take after meals

Prescription ID: S1234567D-2
Date: 2025-04-05 19:08
Symptoms:
- Sore throat
Medicines:
- Cough pills
Notes: Take 3 times a day

Total prescriptions: 2
Use 'view-prescription PRESCRIPTION_ID' to view details and generate HTML.
----------------------------------------------------------------------------------------------------</pre>
<br>

### Viewing specific prescription: `view-prescription`

Views details of a specific prescription and generates a printable HTML version.

Format: `view-prescription PRESCRIPTION_ID`

* `PRESCRIPTION_ID` must be existent in the system
* The HTML file will be generated in the data/prescriptions folder
* Open the HTML file in a web browser to view and print

Example of usage:

`view-prescription S9876543B-1`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Prescription details:
Prescription [S1234567D-1] (2025-04-05 18:52)
Patient ID: S1234567D
Symptoms: 
- Fever
- Cough
Medicines: 
- Paracetamol
- Cough syrup
Notes: Take after meals

Prescription HTML file generated at: C:\Users\Judha Hoka Wishika\Downloads\tp_personal\data\prescriptions\prescription_S1234567D_1.html
Open this file in a web browser to view and print the prescription.
----------------------------------------------------------------------------------------------------</pre>
<br>

### Adding Medical History: `store-history`
Adds one or more entries to a patient's medical history.

Format: `store-history n/NAME ic/NRIC h/HISTORY_ENTRY1, HISTORY_ENTRY2, ...`

* The `NRIC` must be of a patient existing in the system.
* Use commas to separate multiple history entries.

Examples of usage:

`store-history n/Alex Tan ic/S1234567A h/Coughing, Swelling on left leg`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Medical history added for John Doe (NRIC: S1234567D).
----------------------------------------------------------------------------------------------------</pre>
<br>

### Viewing Medical History: `view-history`
Displays medical history of a patient by NRIC or name.

**Format:**
`view-history NRIC` or
`view-history NAME`

**Examples:**
- `view-history S1234567A`
- `view-history Alex Tan`

Expected output:

<pre>----------------------------------------------------------------------------------------------------
Medical History for John Doe (NRIC: S1234567D):
- Diabetes
- Hypertension`
- Coughing
- Swelling on left leg
----------------------------------------------------------------------------------------------------</pre>
<br>

### Editing Medical History: `edit-history`
Modifies a specific entry in a patient’s medical history.

Format: `edit-history ic/NRIC old/OLD_HISTORY_ENTRY new/NEW_HISTORY_ENTRY`

- NRIC must match an existing patient record.
- All history entries are stored as simple strings.
- Viewing by name will display all patients with the given name.
- Editing only replaces the **first matched** old entry.

Example of usage:

`edit-history ic/S1234567A old/Diabetes new/Type 2 Diabetes`

Expected output:

<pre>
Replaced old history "Diabetes" with "Type 2 Diabetes".
----------------------------------------------------------------------------------------------------
Edit-history command executed.
----------------------------------------------------------------------------------------------------</pre>
<br>

### Exiting the program: `bye`
Exits the program.

Format: `bye`
<br>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: You can copy the 'data' folder and paste it in the directory that you save your jar file in another computer. 

--- 

## Known Issues
1. The parameter `NRIC` can take in any String format.
2. The parameter `BIRTHDATE` doesn't have to be a valid past date.
3. The parameter `PHONE` 

## Command Summary

| Action                     | Format, Examples                                                                                                                                                                               |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add patient                | `add-patient n/NAME ic/NRIC dob/BIRTHDATE g/GENDER p/PHONE a/ADDRESS`<br/> e.g. `add-patient n/John Doe ic/S1234567D dob/1999-12-12 g/M p/98765432 a/123 Main Street h/Diabetes, Hypertension` |
| Delete patient             | `delete-patient NRIC`<br/> e.g. `delete-patient S1234567D`                                                                                                                                     |
| Edit patient               | `edit-patient ic/NRIC [n/NAME] [dob/BIRTHDATE] [g/GENDER] [a/ADDRESS] [p/PHONE]`<br/> e.g. `edit-patient ic/S1234567D n/Billy Joe dob/1999-12-21`                                              |
| List patient               | `list-patient`                                                                                                                                                                                 |
| View patient               | `view-patient NRIC`<br/> e.g. `view-patient S1234567D`                                                                                                                                         |
| Store medical history      | `store-history n/NAME ic/NRIC h/MEDICAL_HISTORY`<br/> e.g. `store-history n/John Doe ic/S1234567D h/Depression`                                                                                |
| View medical history       | `view-history NRIC` or `view-history NAME`<br/> e.g. `view-history S1234567D` or `view-history John Doe`                                                                                       |
| Edit medical history       | `edit-history ic/NRIC old/OLD_TEXT new/NEW_TEXT`<br/> e.g. `edit-history ic/S1234567D old/Depression new/Obesity`                                                                              |
| Add appointment            | `add-appointment ic/NRIC dt/DATE t/TIME dsc/DESCRIPTION`<br/> e.g. `add-appointment ic/S1234567D dt/2025-06-15 t/1400 dsc/Annual Checkup`                                                      |
| Delete appointment         | `delete-appointment APPOINTMENT_ID`<br/> e.g. `delete-appointment A123`                                                                                                                        |
| List appointment           | `list-appointment`                                                                                                                                                                             |
| Mark appointment           | `mark-appointment APPOINTMENT_ID`<br/> e.g. `mark-appointment A101`                                                                                                                            |
| Unmark appointment         | `unmark-appointment APPOINTMENT_ID`<br/> e.g. `unmark-appointment A101`                                                                                                                        |
| Sort appointment           | `sort-appointment byDate` or `sort-appointment byId`                                                                                                                                           |
| Find appointment           | `find-appointment PATIENT_NRIC`<br/> e.g. `find-appointment S1234567D`                                                                                                                         |
| Add prescription           | `add-prescription ic/PATIENT_ID s/SYMPTOMS m/MEDICINES [nt/NOTES]`                                                                                                                             |
| View all prescriptions     | `view-all-prescriptions PATIENT_ID`                                                                                                                                                            |
| View specific prescription | `view-prescription PRESCRIPTION_ID`                                                                                                                                                            |

