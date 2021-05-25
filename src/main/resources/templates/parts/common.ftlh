<#macro page>
    <#include "head.ftlh">
        <#include "navbar.ftlh">
        <!--КОНТЕНТ-->
        <div class="container">
            <#nested>
        </div>
        <!--/КОНТЕНТ-->
        <#include "scripts.ftlh">
    <#include "foot.ftlh">
</#macro>