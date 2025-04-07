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

- **Comprehensive Testing**: Created extensive test suite for the prescription subsystem:
    - Unit tests for all prescription-related classes
    - Integration tests for prescription management functionality
    - End-to-end testing of the prescription workflow
    
- **User Experience Enhancements**: Improved user experience through better error handling and guidance:
    - Enhanced error messages for prescription commands to provide clearer guidance on correct syntax
    - Fixed formatting issues in the Parser for view-all-prescriptions and view-prescription commands
    - Ensured consistent error message format across the application
    - Improved user feedback for common command entry mistakes

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

### Features I'm proud of

1. **HTML Prescription Generation**
   - The ability to generate professional-looking prescriptions that doctors can print was a feature I'm particularly proud of implementing. This bridges the gap between digital management and the physical documents patients need.

2. **Comprehensive Prescription Workflow**
   - Creating a complete end-to-end solution that handles everything from prescription creation to storage to retrieval to presentation was a significant achievement.

3. **Robust Testing Framework**
   - The extensive test suite I developed ensures the reliability of the prescription system, with test coverage for all major components and operations.

4. **Technical Documentation**
   - The detailed sequence and class diagrams help future developers understand the architecture and implementation of the prescription management feature.

5. **Architecture Documentation Blueprint**
   - Developed the initial architecture diagram blueprint that formed the foundation for the final design
   - Established the key component relationships that guided the team's understanding of the system
   - Documented component interactions with sequence diagrams
   - Designed the  sequence diagram showing command execution flow
   - Provided the interim architecture diagrams that guided subsequent improvements
   - Collaborated with team members to evolve the architecture representation

### Issues Resolved

During the development and maintenance phase, I identified and fixed several issues to improve the application:

1. **Enhanced Command Format Guidance**: 
   - Improved error messages in the Parser.java file for the view-all-prescriptions and view-prescription commands
   - Made error messages more instructive by clearly indicating the correct command format
   - Ensured consistent error handling across different commands in the application

2. **User Experience Improvements**:
   - Identified and fixed issues where users were receiving unhelpful error messages
   - Created more intuitive feedback for prescription-related commands
   - Enhanced overall user experience with clearer guidance on command syntax

3. **Fixed Prescription Storage Limitation Issue**:
   - Resolved critical bug where the system could not load prescription data beyond 5 entries
   - Fixed "Index out of bounds" error that occurred when loading prescriptions
   - Ensured the system can properly handle and save unlimited number of prescriptions
   - Improved application stability by preventing data loss when storing multiple prescriptions
