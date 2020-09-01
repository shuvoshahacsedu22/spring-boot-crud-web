package com.tigerit.springbootcrudweb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Voter implements Serializable {
        private String nameBangla;
        private String nameEnglish;
        private String pin;
        private String nationalId;

        private String status;
        private String afisStatus;
        private String lockFlag;
        private String death;
        private String voterNo;
        private String formNo;
        private String slNo;
        private String tag;
        private String dateOfBirth;
        private String birthOther;
        private String birthRegistrationNo;
        private String fatherName;
        private String motherName;
        private String spouseName;
        private String gender;
        private String marital;
        private String occupation;
        private String disability;
        private String disabilityOther;
        private Address presentAddress;
        private Address permanentAddress;
        private String education;
        private String educationOther;
        private String educationSub;
        private String bloodGroup;
        private String tin;
        private String driving;
        private String passport;
        private String nidFather;
        private String nidMother;
        private String nidSpouse;
        private String voterNoFather;
        private String voterNoMother;
        private String voterNoSpouse;
        private String phone;
        private String mobile;
        private String religion;
        private String religionOther;
        private String deathDateOfFather;
        private String deathDateOfMother;
        private String deathDateOfSpouse;
        private String noFinger;
        private String noFingerPrint;
        private String voterArea;
        private String voterAt;
        private byte[] picture;
        private byte[] signature;

        public Voter() {
        }

        public Voter(String nameBangla, String nameEnglish, String pin, String nationalId) {
                this.nameBangla=nameBangla;
                this.nameEnglish=nameEnglish;
                this.pin=pin;
                this.nationalId=nationalId;
        }

        public Voter(String nameBangla, String nameEnglish, String pin, String nationalId, String status, String afisStatus, String lockFlag, String death, String voterNo, String formNo, String slNo, String tag, String dateOfBirth, String birthOther, String birthRegistrationNo, String fatherName, String motherName, String spouseName, String gender, String marital, String occupation, String disability, String disabilityOther, Address presentAddress, Address permanentAddress, String education, String educationOther, String educationSub, String bloodGroup, String tin, String driving, String passport, String nidFather, String nidMother, String nidSpouse, String voterNoFather, String voterNoMother, String voterNoSpouse, String phone, String mobile, String religion, String religionOther, String deathDateOfFather, String deathDateOfMother, String deathDateOfSpouse, String noFinger, String noFingerPrint, String voterArea, String voterAt, byte[] picture, byte[] signature) {
                this.nameBangla = nameBangla;
                this.nameEnglish = nameEnglish;
                this.pin = pin;
                this.nationalId = nationalId;
                this.status = status;
                this.afisStatus = afisStatus;
                this.lockFlag = lockFlag;
                this.death = death;
                this.voterNo = voterNo;
                this.formNo = formNo;
                this.slNo = slNo;
                this.tag = tag;
                this.dateOfBirth = dateOfBirth;
                this.birthOther = birthOther;
                this.birthRegistrationNo = birthRegistrationNo;
                this.fatherName = fatherName;
                this.motherName = motherName;
                this.spouseName = spouseName;
                this.gender = gender;
                this.marital = marital;
                this.occupation = occupation;
                this.disability = disability;
                this.disabilityOther = disabilityOther;
                this.presentAddress = presentAddress;
                this.permanentAddress = permanentAddress;
                this.education = education;
                this.educationOther = educationOther;
                this.educationSub = educationSub;
                this.bloodGroup = bloodGroup;
                this.tin = tin;
                this.driving = driving;
                this.passport = passport;
                this.nidFather = nidFather;
                this.nidMother = nidMother;
                this.nidSpouse = nidSpouse;
                this.voterNoFather = voterNoFather;
                this.voterNoMother = voterNoMother;
                this.voterNoSpouse = voterNoSpouse;
                this.phone = phone;
                this.mobile = mobile;
                this.religion = religion;
                this.religionOther = religionOther;
                this.deathDateOfFather = deathDateOfFather;
                this.deathDateOfMother = deathDateOfMother;
                this.deathDateOfSpouse = deathDateOfSpouse;
                this.noFinger = noFinger;
                this.noFingerPrint = noFingerPrint;
                this.voterArea = voterArea;
                this.voterAt = voterAt;
                this.picture = picture;
                this.signature = signature;
        }
}
