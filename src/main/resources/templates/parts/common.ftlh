<#macro page>
    <#include "*/head.ftlh">
        <#include "*/navbar.ftlh">
        <!--КОНТЕНТ-->
        <main class="container">
            <#nested>
        </main>
        <!--/КОНТЕНТ-->
        <#include "*/footer.ftlh">
        <#include "*/scripts.ftlh">
    <#include "*/foot.ftlh">
</#macro>