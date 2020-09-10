<#macro view path name namebtn>
    <#include "head.ftl">
        <#include "navbar.ftl">
        <div class="container">
            <h1 align="center">${name}</h1>
            <form action="${path}" class="mb-3" align="center">
                <button type="submit" class="btn btn-info m-0">${namebtn}</button>
            </form>
            <#nested>
        </div>
        <br/>

        <#include "scripts.ftl">
        <script type="text/javascript">
            jQuery(document).ready(function() {
                jQuery('#table_id').DataTable( {
                    paging: true,
                    "info": false,
                    searching: true,
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.20/i18n/Russian.json"
                    }
                });
            });
        </script>
    <#include "foot.ftl">
</#macro>