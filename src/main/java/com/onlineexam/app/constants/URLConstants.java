package com.onlineexam.app.constants;

public class URLConstants {

	private URLConstants() {
	}

	/*
	 * Services
	 */
	public static final String LOGIN_SERVICE = "/login";
	public static final String LOGIN_VALIDATION = "/validationlogin";
	public static final String CHANGE_PASSWORD = "/changePassword";
	public static final String FORGOT_PASSWORD = "/forgotPassword";
	public static final String REGISTRATION_SERVICE = "/registrationservice";
	public static final String REGISTER_USER = "/registerUser";
	public static final String SAVE_ADDRESS_DETAIL = "/saveaddress";
	public static final String GET_PERSONAL_DETAIL = "/getpersonaldetails";
	public static final String GET_ADDRESS_DETAIL = "/getaddress";
	public static final String USER_SERVICE = "/userregistrationservice";
	public static final String BANK_SERVICE = "/bankservice";
	public static final String SAVE_BANK_DETAIL_SERVICE = "/savebankdetailservice";
	public static final String MASTER_SERVICE = "/masterdata";
	public static final String MASTER_SERVICE_IGNORE = "/masterdata/**";
	public static final String CATEGORY_SERVICE = "/categoryservice";
	public static final String CATEGORY_DETAIL_SERVICE = "/getcategorydetails";
	public static final String SUB_CATEGORY_DETAIL_SERVICE = "/getsubcategorydetails";
	public static final String COUNTRY_SERVICE = "/countryservice";
	public static final String COUNTRY_DETAILS = "/getcountrydetails";
	public static final String STATE_SERVICE = "/stateservice";
	public static final String STATE_DETAIL = "/getstatedetails";
	public static final String CITY_SERVICE = "/cityservice";
	public static final String CITY_DETAIL = "/getcitydetails";
	public static final String TOKEN_SERVICE = "/tokenservice";
	public static final String VALIDATE_TOKEN = "/validateToken";
	public static final String ABOUTME_SERVICE = "/aboutme";
	public static final String SAVE_ABOUTME_SERVICE = "/saveaboutme";
	public static final String GET_ABOUTME_SERVICE = "/getaboutme";
	public static final String FILE_SERVICE = "/fileservice";
	public static final String FILE_SAVE_SERVICE = "/savefile";
	public static final String FILE_GET_SERVICE = "/getfile";
	public static final String COMPANY_SERVICE = "/companyservice";
	public static final String COMPANY_DETAIL = "/getcompanydetails";
	public static final String GST_SERVICE = "/gstservice";
	public static final String SAVE_GST_DETAILS = "/savegstdetails";
	public static final String GET_GST_DETAILS = "/getgstdetails";
	public static final String PAN_CARD_SERVICE = "/pancarservice";
	public static final String SAVE_PANCARD_DETAILS = "/savepancarddetails";
	public static final String GET_PANCARD_DETAILS = "/getpancarddetails";
	public static final String GET_BANK_DETAILS = "/getbankdetails";
	public static final String LOGOUT_SERVICE = "/logoutservice";
	public static final String LOGOUT = "/logout";
	public static final String GENDER_SERVICE = "/genderservice";
	public static final String GENDER_DETAILS = "/genderdetails";
	public static final String IMAGES_URL = "/images-files/**";
	public static final String MENU_DETAILS_FOR_USER = "/usermenus";
	public static final String MENU_DETAILS = "/menudetails";
	public static final String MENU_SERVICE = "/menuservice";
	public static final String STUDENT_SERVICE = "/studentservice";
	public static final String QUESTION_SERVICE = "/questionservice";
	public static final String SAVES_ROLES = "/saveroles";
	public static final String UPDATE_ROLES = "/editroles";
	public static final String DELETE_ROLES = "/deleteroles";
	public static final String FETCH_ALL_ROLES = "/getAllRoles/{pageIndex}/{totalRecords}";
	public static final String FETCH_ROLEAUTH_BY_ROLE = "/getRoleAuthByRole/{roleId}";
	public static final String CREATE_ROLEAUTH = "/cretaeRoleAuth";
	public static final String UPDATE_ROLEAUTH = "/updateRoleAuth";
	public static final String UPDATE_USER = "/updateUser";
	public static final String DELETE_USER = "/updateUserStatus";
	public static final String FETCH_ALL_USERS = "/getAllUsers/{pageIndex}/{totalRecords}";
	public static final String SAVES_SUBJECTS = "/savesubjects";
	public static final String SAVES_SUB_SUBJECTS = "/savesubsubjects";
	public static final String SAVES_CLASSES = "/saveclasses";
	public static final String SAVES_DIVISIONS = "/savedivisions";
	public static final String SAVES_COURSES = "/savecourses";
	public static final String SAVES_STUDENTS = "/savestudents";
	public static final String SAVES_TOPICS = "/savetopics";
	public static final String SAVES_QUESTIONS = "/savequestions";
	public static final String SAVES_QUESTIONS_PAPER = "/savequestionPaper";
	public static final String UPDATE_SUBJECTS = "/editsubjects";
	public static final String UPDATE_SUB_SUBJECTS = "/editsubsubjects";
	public static final String UPDATE_CLASSES = "/editclasses";
	public static final String UPDATE_DIVISIONS = "/editdivisions";
	public static final String UPDATE_COURSES = "/editcourses";
	public static final String UPDATE_STUDENTS = "/editstudents";
	public static final String UPDATE_TOPICS = "/edittopics";
	public static final String UPDATE_QUESTIONS = "/editquestions";
	public static final String UPDATE_QUESTIONS_PAPER = "/editquestionPaper";
	public static final String DELETE_SUBJECTS = "/deletesubjects";
	public static final String DELETE_SUB_SUBJECTS = "/deletesubsubjects";
	public static final String DELETE_CLASSES = "/deleteclasses";
	public static final String DELETE_DIVISIONS = "/deletedivisions";
	public static final String DELETE_COURSES = "/deletecourses";
	public static final String DELETE_STUDENTS = "/deletestudents";
	public static final String DELETE_TOPICS = "/deletetopics";
	public static final String DELETE_QUESTIONS = "/deletequestions";
	public static final String DELETE_QUESTIONS_PAPER = "/deletequestionPaper";
	public static final String FETCH_ALL_SUBJECTS = "/getAllSubjects/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_SUB_SUBJECTS = "/getAllSubSubjects/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_SUB_SUBJECTS_BY_FILTER = "/getAllSubSubjectsByFilter";
	public static final String FETCH_ALL_DIVISION_BY_FILTER = "/getAllDivisionsByFilter";
	public static final String FETCH_ALL_CLASS_BY_FILTER = "/getAllClassesByFilter";
	public static final String FETCH_ALL_CLASSES = "/getAllClasses/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_DIVISIONS = "/getAllDivisions/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_COURSES = "/getAllCourses/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_STUDENTS = "/getAllStudents/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_TOPICS = "/getAllTopics/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_QUESTIONS = "/getAllQuestions/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_QUESTIONS_PAPER = "/getAllQuestionPaper/{pageIndex}/{totalRecords}";
	public static final String FETCH_ALL_QUESTIONS_BY_FILTER = "/getQuestionByFilter";
	public static final String FETCH_ALL_QUESTIONS_PAPER_BY_FILTER = "/getQuestionPaperByFilter";
	public static final String FETCH_ALL_TOPICS_BY_FILTER = "/getAllTopicsByFilter";

}
