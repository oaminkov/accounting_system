<#include "parts/head.ftl">
    <#include "parts/navbar.ftl">
    <div align="center" class="" style="width: 98%; margin: 0 auto">
        <h1>Информационные ресурсы</h1>

        <a href="/add_informationproduct" class="btn btn-info m-0 mb-3">Создать новый информационный ресурс</a>

        <table id="table_id" class="table table-striped table-bordered mt-0" style="width:100%">
            <thead>
                <tr>
                    <th>Инвентарный номер</th>
                    <th>Страна</th>
                    <th>Дисциплина наблюдений</th>
                    <th>Вид наблюдений</th>
                    <th>Период наблюдений</th>
                    <th>Территория наблюдений</th>
                    <th>Организация</th>
                    <th>Загруженные файлы</th>
                    <th>Просмотреть полностью</th>
                    <th>Изменить</th>
                    <th>Удалить</th>
                </tr>
            </thead>
            <tbody>
            <#list listInformationProducts as informationProduct>
                <tr>
                    <td>${informationProduct.inventoryNumber}</td>
                    <td>${informationProduct.country.nameCountry}</td>
                    <td>${informationProduct.observationDiscipline.nameObservationDiscipline}</td>
                    <td>${informationProduct.observationType.nameObservationType}</td>
                    <td>${informationProduct.dateObservationStart}<br>${informationProduct.dateObservationEnd}</td>
                    <td>${informationProduct.geographicalObject.nameGeographicalObject}</td>
                    <td>${informationProduct.organization.fullnameOrganization}</td>
                    <td>
                        <#if auth_user??>
                            <div class="btn-group">
                                <form action="/information_product/download/${informationProduct.idInformationProduct}">
                                    <button type="submit" class="btn btn-sm btn-secondary">Скачать</button>
                                </form>
                                <button type="button" class="btn btn-sm btn-secondary dropdown-toggle px-2" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false" data-reference="parent">
                                    <span class="sr-only">Toggle Dropdown</span>
                                </button>
                                <div class="dropdown-menu">
                                    <#list uploadedFiles as uploadedFile>
                                        <#if informationProduct.idInformationProduct == uploadedFile.informationProduct.idInformationProduct>
                                            <a href="/file/${informationProduct.organization.idOrganization}/${informationProduct.inventoryNumber}/${uploadedFile.nameUploadedFile}"
                                               class="dropdown-item" target="_blank">${uploadedFile.nameUploadedFile}</a>
                                        </#if>
                                    </#list>
                                </div>
                            </div>
                        </#if>
                    </td>
                    <td>
                        <form action="information_product/view/${informationProduct.idInformationProduct}"><button type="submit" class="btn btn-indigo btn-sm m-0">Просмотреть</button></form>
                    </td>
                    <td>
                        <#if auth_user??>
                            <form action="information_product/edit/${informationProduct.idInformationProduct}"><button type="submit" class="btn btn-warning btn-sm m-0">Редактировать</button></form>
                        </#if>
                    </td>
                    <td>
                        <#if auth_user??>
                            <form action="information_product/delete/${informationProduct.idInformationProduct}"><button type="submit" class="btn btn-danger btn-sm m-0">Удалить</button></form>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <#include "parts/scripts.ftl">
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
<#include "parts/foot.ftl">
