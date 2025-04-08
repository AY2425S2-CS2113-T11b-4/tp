# Wen Yee's Project Portfolio Page

## Project: ClinicEase

**ClinicEase** is a CLI-based clinic management program that helps doctors - our target users - to efficiently manage patient records, 
medical histories, and appointments. The system enables user to view and update patient details, 
manage appointments, maintain medical history records, and manage prescriptions.  
This project aims to simplify clinic administrative tasks with a user-friendly interface.

## Summary of Contributions
Code Dashboard Link: [Wen Yee's Code Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=chwenyee&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
 
### Enhancement Implemented

1. Reformat the UI output to ensure a consistent and aligned display format across the program.
2. Introduced custom exceptions e.g. `DuplicatePatientIDException`, `UnknownCommandException` and `InvalidInputFormatException` 
in v1.0 to enable more graceful error handling.
3. Improved the logic of `Parser.extractValue()` to correctly extract user input parameters, especially when both `dt/` and `t/`
are present.
4. Restructured the code in `ClinicEase.java` and `Parser.java`, and created a `command` package to make the codebase more OOP after v1.0.
5. Linked `Appointment` Class to `Patient` Class to ensure appointments stored in a `Patient` object are kept in sync 
with the appointment list in `ManagementSystem`.
6. Changed the data type of `dateTime` variable in `Appointment` to be `LocalDateTime` for better input validation of 
`dt/` and `t/`, and to simplify the implementation of the `sort-appointment` feature.
7. Added comprehensive validation for: NRIC format compliance, appointment scheduling conflicts (throws `AppointmentClashException`) 
and future-dated appointments (prevents past-date/time entries)
8. Implemented cleanup of associated appointment records when the patient is deleted.
9. Added JavaDoc for files such as AddAppointmentCommand, DeleteAppointmentCommand, ListAppointmentCommand, SortAppointmentCommand,
Appointment, ExitCommand, HelpCommand, and Parser. 

#### Features Implemented
- `add-appointment`: Adds a new appointment.
- `delete-appointment`: Deletes a specified appointment.
- `sort-appointment`: Sorts appointments by `byDate` or `byId`.
- `help`: Lists all available commands.

#### Contributions to the UG:
- Wrote sections: Table of Contents, Quick Start, Features (`help`, `add-appointment`, `delete-appointment`, `sort-appointment`, `bye`).
- Added "NOTES" on feature's command syntax, parameters (`NRIC`, `DATE`, `TIME`, `APPOINTMENT_ID`).
- Wrote FAQ: data saving, data transfer, risks of editing files.
- Documented Known Issue: space omission in command parsing.
- Wrote Command Summary table with formats & examples of usage.

#### Contributions to the DG:
- Documented `Parser` component design:
  - Wrote explanation of parsing workflow.
  - Created diagrams: `parserClassDiagram.png`, `parserSequence.png`.
- Add & Delete Appointment Features:
  - Documented implementation details & usage scenarios.
  - Diagrams: `addAppointmentSequence.png`, `deleteAppointmentSequence.png`.
  - Explained rationale & rejected alternatives.
- Other sections: Table of Contents, Non-functional Requirements, Glossary.
- Non-functional requirements
- Glossary 

#### Contributions to team-based tasks:

- Setting up the Github team repo
- Maintaining the issue tracker e.g. issue and milestones
- Release management e.g. wrapping up milestones, saving DG and UG as PDFs

#### Review/mentoring contributions:

- Helped teammates resolve Git merge conflicts and guided them on pull request practices.
- Reminding teammates of important deadlines.
- Helped to test out the newly-implemented features to ensure the feature works as desired.
- Discussed with teammates on what to include for Design and Implementation section in Developer Guide.
- Helped review the coding style consistency 

<div style="page-break-after: always;"></div>

#### Contributions to the Developer Guide 

**Diagrams I contributed:**

Parser Class Diagram:
![parser-class-diagram](../diagrams/parserClassDiagram.png)

Parser Sequence Diagram:
![parser-sequence-diagram](../diagrams/parserSequence.png)

Add Appointment Sequence Diagram:
![add-appointment](../diagrams/addAppointmentSequence.png)

Delete Appointment Sequence Diagram:
![delete-appointment](../diagrams/deleteAppointmentSequence.png)


