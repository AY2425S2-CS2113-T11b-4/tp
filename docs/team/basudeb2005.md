# Basudeb Chakraborty - Project Portfolio Page

## Overview

ClinicEase is a desktop application designed for clinic management. It provides doctors with a command-line interface to manage patients, appointments, and prescriptions. The application enables doctors to track patient information, medical histories, schedule appointments, and create detailed prescriptions with HTML export capabilities.

## Summary of Contributions

### Code contributed
[RepoSense Code Dashboard](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=basudeb2005)

### Enhancements implemented

#### 1. Core Application Structure
I designed and implemented the foundational structure of the application, including:
- Initial UI class setup with welcome/goodbye messages and command reading functionality
- Parser framework for processing user commands
- Basic application workflow in ClinicEase main class

#### 2. Appointment Management System
I implemented key appointment features:
- Appointment class design with unique IDs and status tracking
- Add appointment command to schedule patient appointments
- Delete appointment functionality to remove unnecessary appointments
- Appointment display functionality in UI with proper formatting

#### 3. Prescription Management System
I implemented the complete Prescription Management System, a key feature that enables doctors to create and manage patient prescriptions in the ClinicEase application:
- **Prescription Class Design**: Designed and implemented the `Prescription` class with essential attributes and functionality:
    - Storage of patient details, symptoms, medicines, and notes
    - Automatic generation of unique prescription IDs
    - Timestamp recording for each prescription

- **Prescription Commands**: Implemented three main commands for managing prescriptions:
    - `add-prescription`: Allows doctors to create new prescriptions with patient ID, symptoms, medicines, and optional notes
    - `view-all-prescriptions`: Shows all prescriptions for a specific patient
    - `view-prescription`: Displays details of a specific prescription by ID

- **HTML Generation**: Created a feature that generates HTML prescription documents for printing:
    - Professional layout with CSS styling
    - Organized sections for patient information, symptoms, medicines, and special instructions
    - Print functionality via a button in the HTML document

- **Storage and Persistence**: Implemented storage functionality to save and retrieve prescriptions from file system:
    - Custom serialization format for prescriptions
    - Load and save operations for persistent storage

### Contributions to the User Guide

- Added the "Managing Prescriptions" section, documenting:
    - Command format and examples for adding prescriptions
    - Instructions for viewing all prescriptions for a patient
    - Instructions for viewing specific prescriptions and generating HTML reports
    - Command summary section for all prescription-related commands

### Contributions to the Developer Guide

- Added documentation for the core application structure
- Added documentation for the appointment management system
- Added documentation for the prescription management features, including:
    - Use cases for adding, viewing, and generating prescriptions
    - Manual testing instructions for prescription management
    - User stories related to prescription functionality

### Contributions to team-based tasks

- Set up the initial application architecture that the team built upon
- Integrated the prescription management subsystem with the existing patient management system
- Ensured compatibility of prescription commands with the overall command parsing structure
- Collaborated on refining the storage mechanisms to handle multiple data types
