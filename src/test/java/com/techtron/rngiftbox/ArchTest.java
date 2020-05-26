package com.techtron.rngiftbox;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.techtron.rngiftbox");

        noClasses()
            .that()
            .resideInAnyPackage("com.techtron.rngiftbox.service..")
            .or()
            .resideInAnyPackage("com.techtron.rngiftbox.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.techtron.rngiftbox.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
