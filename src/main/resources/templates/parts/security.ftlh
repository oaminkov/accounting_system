<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        auth_user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        auth_username = auth_user.getUsername()
        auth_isAdmin = auth_user.isAdmin()
    >
<#else>
    <#assign
        auth_username = "Гость"
        auth_isAdmin = false
    >
</#if>