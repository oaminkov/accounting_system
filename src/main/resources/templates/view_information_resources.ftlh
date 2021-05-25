<#include "parts/head.ftlh">
    <#include "parts/navbar.ftlh">
    <div align="center" style="width:99%; margin:0 auto;">
        <h2>Информационные ресурсы</h2>

        <a href="/information_resources/add" class="btn btn-primary mb-3">Создать новый информационный ресурс</a>

        <table id="table_id" class="table table-striped table-bordered mt-0">
            <thead>
                <tr>
                    <th>Инвентарный номер</th>
                    <th>Страна</th>
                    <th>Период наблюдений</th>
                    <th>Загруженные файлы</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <#list informationResources as informationResource>
                <tr>
                    <td>${informationResource.inventoryNumber}</td>
                    <td>${informationResource.country.name}</td>
                    <td>${informationResource.dateObservationStart}<br>${informationResource.dateObservationEnd}</td>
                    <td>
                        <#if auth_user??>
                            <#if informationResource.uploadedFiles?has_content>
                                <div class="btn-group">
                                    <form action="/information_resources/download/${informationResource.id}">
                                        <button type="submit" class="btn btn-sm btn-secondary">Скачать</button>
                                    </form>
                                    <button type="button" class="btn btn-sm btn-secondary dropdown-toggle px-2" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false" data-reference="parent">
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <div class="dropdown-menu">
                                        <#list informationResource.uploadedFiles as uploadedFile>
                                            <a href="/file/${informationResource.country.id}/${informationResource.mainOrganization.id}/${informationResource.inventoryNumber}/${uploadedFile.name}"
                                               class="dropdown-item" target="_blank">${uploadedFile.name}</a>
                                        </#list>
                                    </div>
                                </div>
                            <#else>
                                <p align="center">Файлы отсутствуют</p>
                            </#if>
                        </#if>
                    </td>
                    <td>
                        <form action="/information_resources/${informationResource.id}"><button type="submit" class="btn btn-sm btn-info m-0">Просмотреть</button></form>
                    </td>
                    <td>
                        <#if auth_user??>
                            <form action="/information_resources/edit/${informationResource.id}"><button type="submit" class="btn btn-warning btn-sm m-0">Редактировать</button></form>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <#include "parts/scripts.ftlh">
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
<#include "parts/foot.ftlh">