package org.example.project.data.database

import org.example.project.data.database.dao.ActiveSubstanceDao
import org.example.project.data.database.dao.ActiveSubstanceReportDao
import org.example.project.data.database.dao.GOSTDao
import org.example.project.data.database.dao.GOSTMedicineDao
import org.example.project.data.database.dao.MedicalOfficerDao
import org.example.project.data.database.dao.MedicinalFormDao
import org.example.project.data.database.dao.MedicinalFormReportDao
import org.example.project.data.database.dao.MedicineDao
import org.example.project.data.database.dao.ReportDao
import org.example.project.data.database.dao.ReportMedicineDao
import org.example.project.data.database.dao.SpecialityDao
import org.example.project.data.database.dao.StandardDao
import org.example.project.data.database.dao.StatusDao
import org.example.project.domain.entities.ActiveSubstance
import org.example.project.domain.entities.ActiveSubstanceReport
import org.example.project.domain.entities.GOST
import org.example.project.domain.entities.GOSTMedicine
import org.example.project.domain.entities.MedicalOfficer
import org.example.project.domain.entities.MedicinalForm
import org.example.project.domain.entities.MedicinalFormReport
import org.example.project.domain.entities.Medicine
import org.example.project.domain.entities.Report
import org.example.project.domain.entities.ReportMedicine
import org.example.project.domain.entities.Speciality
import org.example.project.domain.entities.Standard
import org.example.project.domain.entities.Status

fun ActiveSubstanceDao.toDomain(): ActiveSubstance {
    return ActiveSubstance(
        id = this.id.value,
        name = this.name,
        composition = this.composition,
        appointment = this.appointment,
        medicalOfficerId = this.medicalOfficerDao.id.value
    )
}

fun ActiveSubstanceReportDao.toDomain(): ActiveSubstanceReport {
    return ActiveSubstanceReport(
        id = this.id.value,
        activeSubstanceId = this.activeSubstanceDao.id.value,
        reportId = this.reportDao.id.value,
        reportMedicalOfficerId = this.medicalOfficerDao.id.value
    )
}

fun GOSTDao.toDomain(): GOST {
    return GOST(
        id = this.id.value,
        name = this.name
    )
}

fun GOSTMedicineDao.toDomain(): GOSTMedicine {
    return GOSTMedicine(
        id = this.id.value,
        gostId = this.gostDao.id.value,
        medicineId = this.medicineDao.id.value
    )
}

fun MedicalOfficerDao.toDomain(): MedicalOfficer {
    return MedicalOfficer(
        id = this.id.value,
        firstName = this.firstName,
        lastName = this.lastName,
        surname = this.surname,
        email = this.email,
        workExperience = this.workExperience?: 0,
        specialityId = this.specialityDao.id.value,
        age =  this.age?: 0,
        numberChild = this.numberChild?: 0
    )
}

fun MedicinalFormDao.toDomain(): MedicinalForm {
    return MedicinalForm(
        id = this.id.value,
        name = this.name,
        composition = this.composition,
        medicalOfficerId = this.medicalOfficerDao.id.value
    )
}

fun MedicinalFormReportDao.toDomain(): MedicinalFormReport {
    return MedicinalFormReport(
        id = this.id.value,
        reportId = this.reportDao.id.value,
        reportMedicalOfficerId = this.medicalOfficerDao.id.value,
        medicinalFormId = this.medicinalFormDao.id.value
    )
}

fun MedicineDao.toDomain(): Medicine {
    val activeSubstanceDao = ActiveSubstanceDao.findById(this.activeSubstanceDao.id.value)
        ?: throw Exception("Active Substance not found")
    val medicinalFormDao = MedicinalFormDao.findById(this.medicinalFormDao.id.value)
        ?: throw Exception("Medicinal Form not found")
    val standardDao = StandardDao.findById(this.standardDao.id.value)
        ?: throw Exception("Standard not found")
    return Medicine(
        id = this.id.value,
        producer = this.producer,
        name = this.name,
        dateProduce = this.dateProduce,
        activeSubstanceId = this.activeSubstanceDao.id.value,
        activeSubstanceName = activeSubstanceDao.name,
        medicinalFormId = this.medicinalFormDao.id.value,
        medicinalFormName = medicinalFormDao.name,
        standardId = this.standardDao.id.value,
        standardName = standardDao.name
    )
}

fun ReportDao.toDomain(): Report {
    val medicalOfficerDao = MedicalOfficerDao.findById(this.medicalOfficerDao.id.value)
        ?: throw Exception("Medical Officer not found")
    return Report(
        id = this.id.value,
        name = this.name,
        date = this.date,
        medicalOfficerId = medicalOfficerDao.id.value,
        medicalOfficerName = "${medicalOfficerDao.surname} ${medicalOfficerDao.firstName} ${medicalOfficerDao.lastName}"
    )
}

fun ReportMedicineDao.toDomain(): ReportMedicine {
    return ReportMedicine(
        id = this.id.value,
        reportId = this.reportDao.id.value,
        reportMedicalOfficerId = this.medicalOfficerDao.id.value,
        medicineId = this.medicineDao.id.value
    )
}

fun SpecialityDao.toDomain(): Speciality {
    return Speciality(
        id = this.id.value,
        name = this.name,
        duties = this.duties
    )
}

fun StandardDao.toDomain(): Standard {
    return Standard(
        id = this.id.value,
        name = this.name,
        components = this.components
    )
}

fun StatusDao.toDomain(): Status {
    return Status(
        id = this.id.value,
        startData = this.startData,
        endData = this.endData,
        reasonOfChange = this.reasonOfChange
    )
}