
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<spring:message var="pageTitle" code="newUserRegistration.pageTitle" />
<spring:message var="msgAllFieldsRequired"
	code="newUserRegistration.message.allFieldsRequired" />

<html>
<head>
<title>${pageTitle}</title>
</head>
<body>
	<form:form action="." modelAttribute="account">
		<form:errors path="*">
			<%-- <div>
				<spring:message code="error.global" />
			</div> --%>
		</form:errors>
		<h1>${pageTitle}</h1>
		<div>${msgAllFieldsRequired}</div>
		<div>
			<spring:message code="newUserRegistration.label.username" />
			<form:input path="username" />
		</div>

		<div>
			<form:errors path="username" htmlEscape="false" />
		</div>

		<div>
			Password:
			<form:password path="password" />
		</div>
		<div>
			Confirm password:
			<form:password path="confirmPassword" />
		</div>
		<div>
			E-mail address:
			<form:input path="email" />
		</div>
		<div>
			First name:
			<form:input path="firstName" />
		</div>
		<div>
			Last name:
			<form:input path="lastName" />
		</div>
		<div>
			<form:checkbox id="marketingOk" path="marketingOk" />
			Please send me product updates by e-mail.
		</div>
		<div>
			<form:checkbox id="acceptTerms" path="acceptTerms" />
			I accept the <a href="#">terms of use</a>.
		</div>
		<div>
			<input type="submit" value="Register" />
		</div>
	</form:form>
</body>
</html>