package org.example.project.di


import org.example.project.data.repositories.ActiveSubstanceRepositoryImpl
import org.example.project.data.repositories.GOSTRepositoryImpl
import org.example.project.data.repositories.MedicalOfficerRepositoryImpl
import org.example.project.data.repositories.MedicinalFormRepositoryImpl
import org.example.project.data.repositories.MedicineRepositoryImpl
import org.example.project.data.repositories.ReportRepositoryImpl
import org.example.project.data.repositories.SpecialityRepositoryImpl
import org.example.project.data.repositories.StandardRepositoryImpl
import org.example.project.data.repositories.StatusRepositoryImpl
import org.example.project.domain.repositories.ActiveSubstanceRepository
import org.example.project.domain.repositories.GOSTRepository
import org.example.project.domain.repositories.MedicalOfficerRepository
import org.example.project.domain.repositories.MedicinalFormRepository
import org.example.project.domain.repositories.MedicineRepository
import org.example.project.domain.repositories.ReportRepository
import org.example.project.domain.repositories.SpecialityRepository
import org.example.project.domain.repositories.StandardRepository
import org.example.project.domain.repositories.StatusRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ActiveSubstanceRepository> { ActiveSubstanceRepositoryImpl() }
    single<GOSTRepository> { GOSTRepositoryImpl() }
    single<MedicalOfficerRepository> { MedicalOfficerRepositoryImpl() }
    single<MedicinalFormRepository> { MedicinalFormRepositoryImpl() }
    single<MedicineRepository> { MedicineRepositoryImpl() }
    single<ReportRepository> { ReportRepositoryImpl() }
    single<SpecialityRepository> { SpecialityRepositoryImpl() }
    single<StandardRepository> { StandardRepositoryImpl() }
    single<StatusRepository> { StatusRepositoryImpl() }
}
