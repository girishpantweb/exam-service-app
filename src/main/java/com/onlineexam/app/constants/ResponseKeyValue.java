package com.onlineexam.app.constants;

public enum ResponseKeyValue {

	LOCALE_KEY("localekey"), CUSTOM_RESPONSE_KEY("customResponse"), OTP_RESPONSE_KEY("otpResponse"),
	REGISTRATION_RESPONSE_KEY("registrationResponse"), SAVE_BANK_DETAILS_RESPONSE_KEY("bankDetailsResponse"),
	EXCEPTION_KEY("exception"), EXCEPTION_DATE_KEY("exceptionDate"), TOKEN_RESPONSE_KEY("validationToken"),
	SMS_RESPONSE_KEY("smsResponse"), PARTNER_BASIC_PROFILE_REDIRECTION("partnerPage"),
	CATEGORY_RESPONSE_KEY("categoryData"), SUB_CATEGORY_RESPONSE_KEY("subCategoryData"),
	STATE_RESPONSE_KEY("stateData"), CITY_RESPONSE_KEY("cityData"), COUNTRY_RESPONSE_KEY("countryData"),
	VALIDATE_TOKEN_RESPONSE_KEY("validateToken"), ABOUT_ME_RESPONSE_KEY("abouMeResponse"),
	FILE_SAVE_RESPONSE_KEY("fileSaveResponse"), COMPANY_DATA_KEY("companyData"), GST_SAVE_KEY("gstSaveResponse"),
	GST_GET_KEY("gstResponse"), PANCARD_GET_KEY("panCardResponse"), PANCARD_SAVE_KEY("panCardSaveResponse"),
	GENDER_RESPONSE_KEY("genderData"), BANK_GET_KEY("bankResponse"),
	PERSONAL_DETAILS_RESPONSE_KEY("personalDetailsResponse"), MENU_DETAILS_RESPONSE_KEY("menuDetailsResponse"),
	ADDRESS_RESPONSE_KEY("addressResponse"), ROLE_AUTHORIZATION_RESPONSE_KEY("roleAuthorizationResponse"),
	ROLE_MASTER_DATA_KEY("allRoles"), ROLE_MASTER_ALL_DATA_COUNT_KEY("allRolesCount"),
	SUBJECT_MASTER_DATA_KEY("allSubjects"), SUBJECT_MASTER_ALL_DATA_COUNT_KEY("allSubjectsCount"),
	COURSE_MASTER_DATA_KEY("allCourses"), COURSE_MASTER_ALL_DATA_COUNT_KEY("allCoursesCount"),
	DIVISION_MASTER_DATA_KEY("allDivisions"), DIVISION_MASTER_ALL_DATA_COUNT_KEY("allDivisionsCount"),
	CLASS_MASTER_DATA_KEY("allClasses"), CLASS_MASTER_ALL_DATA_COUNT_KEY("allClassesCount"),
	USER_MASTER_DATA_KEY("allUsersDetail"), USER_MASTER_ALL_DATA_COUNT_KEY("allUsersCount"),
	STUDENT_DATA_KEY("allStudents"), STUDENT_ALL_DATA_COUNT_KEY("allStudentsCount"),
	SUB_SUBJECT_MASTER_DATA_KEY("allSubSubjects"), SUB_SUBJECT_MASTER_ALL_DATA_COUNT_KEY("allSubSubjectsCount"),
	TOPIC_MASTER_DATA_KEY("allTopics"), TOPIC_MASTER_ALL_DATA_COUNT_KEY("allTopicsCount"),
	QUESTION_DATA_KEY("allQuestions"), QUESTION_ALL_DATA_COUNT_KEY("allQuestionsCount"),
	QUESTION_SET_DATA_KEY("allQuestionsSet"), EXAM_DATA_KEY("allExams"), EXAM_ALL_DATA_COUNT_KEY("allExamsCount"),
	ASSIGN_STUDENT_DATA_KEY("dataKey"), ASSIGN_EXAM_DATA_KEY("allAssignExam"),
	ASSIGN_EXAM_DATA_COUNT_KEY("allAssignExamCount"), ASSIGN_QUESTIONS_FOR_STUDENTS("examQuestions"),
	STUDENTS_EXAM_INFO("examInfo"), DATA_KEY("dataKey");

	private String key;

	private ResponseKeyValue(String key) {
		this.key = key;
	}

	public String key() {
		return this.key;
	}

}
