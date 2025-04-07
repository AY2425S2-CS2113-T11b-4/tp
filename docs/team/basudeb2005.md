# Basudeb Chakraborty - Project Portfolio Page

## Overview

ClinicEase is a desktop application for clinic management with a command-line interface for doctors to manage patients, appointments, and prescriptions.

## Summary of Contributions

### Code contributed
[RepoSense Code Dashboard](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=basudeb2005)

### Enhancements implemented

#### 1. Core Application Structure
- Initial UI class setup with welcome/goodbye messages and command reading functionality
- Parser framework for processing user commands
- Basic application workflow in ClinicEase main class

#### 2. Appointment Management System
- Appointment class design with unique IDs and status tracking
- Add/delete appointment commands with proper UI display formatting

#### 3. Prescription Management System
- **Prescription Class Design**: Created `Prescription` class with patient details, symptoms, medicines, automatic ID generation, and timestamps

- **Prescription Commands**: Implemented `add-prescription`, `view-all-prescriptions`, and `view-prescription` commands

- **HTML Generation**: Created feature to generate professional HTML prescriptions with print functionality

- **Storage and Persistence**: Implemented custom serialization format with load/save operations

- **Comprehensive Testing**: Created unit, integration, and end-to-end tests for the prescription subsystem

### Contributions to Documentation

#### User Guide
- Added "Managing Prescriptions" section with command formats, examples, and instructions

#### Developer Guide
- Added documentation for core application structure and appointment system
- Created prescription management documentation including use cases and testing instructions

### Team Contributions & Issue Resolution

- Set up initial application architecture
- Integrated subsystems and ensured command compatibility
- Enhanced command format guidance with improved error messages
- Fixed critical "Index out of bounds" bug in prescription storage system that prevented loading more than 5 prescriptions

### Key Features Implemented

1. **HTML Prescription Generation**: Professional-looking printable prescriptions
2. **End-to-End Prescription Management**: Complete workflow from creation to storage to retrieval
3. **Robust Testing Framework**: Comprehensive test coverage for reliability
4. **Technical Documentation**: Created sequence and class diagrams for developer guidance