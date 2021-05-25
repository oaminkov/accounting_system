<#macro view path name namebtn>
    <#include "head.ftlh">
        <#include "navbar.ftlh">
        <div class="container mb-3">
            <h2 class="mb-3" align="center">${name}</h2>
            <form action="${path}" class="mb-3" align="center">
                <button type="submit" class="btn btn-primary m-0">${namebtn}</button>
            </form>
            <#nested>
        </div>

        <#include "scripts.ftlh">
        <script type="text/javascript">
            jQuery(document).ready(function() {
                jQuery('#table_id').DataTable( {
                    paging: true,
                    info: true,
                    searching: true,
                    language: {
                        url: "//cdn.datatables.net/plug-ins/1.10.20/i18n/Russian.json"
                    }
                });
            });
        </script>
    <#include "foot.ftlh">
</#macro>