jQuery(document).ready(function () {
    function getOptions(result) {
        if (result.type === 'error') {
            alert('error');
            return "";
        }
        else {
            let options = '';

            $(result).each(function(key, val) {
                options += '<option value="' + val.id + '">' + val.name + '</option>';
            });

            return options;
        }
    }

    function ajaxRequest(requestAddress, id, parentDiv, changedObj, changedObjHtml) {
        $.ajax({
            type: 'GET',
            url: requestAddress,
            data: {
                id: id
            },
            dataType: 'json',
            success: function (result) {
                let options = getOptions(result);

                parentDiv.find(changedObj).html(changedObjHtml + options);
                parentDiv.find(changedObj).attr('disabled', false);
            }
        });
    }

    function changeTypes() {
        let requestAddress = "/getObservationTypeList",
            idDisc = $(this).val(),
            parentDiv = $(this).parents('.discDiv').first(),
            changedClass = '.getObservationType',
            changedClassHtml = '<option value="0">-- Выберите вид наблюдений --</option>';

        if (idDisc === '0' || idDisc === '-1') {
            parentDiv.find(changedClass).html(changedClassHtml);
            parentDiv.find(changedClass).attr('disabled', true);
            parentDiv.find('.getObservationParameter').html('<option value="0">-- Выберите параметр наблюдений --</option>');
            parentDiv.find('.getObservationParameter').attr('disabled', true);
            return(false);
        }

        parentDiv.find(changedClass).attr('disabled', true);
        parentDiv.find(changedClass).html('<option>загрузка...</option>');
        parentDiv.find('.getObservationParameter').html('<option value="0">-- Выберите параметр наблюдений --</option>');
        parentDiv.find('.getObservationParameter').attr('disabled', true);

        ajaxRequest(requestAddress, idDisc, parentDiv, changedClass, changedClassHtml);
    }

    function changeParameters() {
        let requestAddress = "/getObservationParameterList",
            idType = $(this).val(),
            parentDiv = $(this).parents('.typeDiv').first(),
            changedClass = '.getObservationParameter',
            changedClassHtml = '<option value="0">-- Выберите параметр наблюдений --</option>';

        if (idType === '0' || idType === '-1') {
            parentDiv.find(changedClass).html(changedClassHtml);
            parentDiv.find(changedClass).attr('disabled', true);
            return(false);
        }

        parentDiv.find(changedClass).attr('disabled', true);
        parentDiv.find(changedClass).html('<option>загрузка...</option>');

        ajaxRequest(requestAddress, idType, parentDiv, changedClass, changedClassHtml);
    }

    $('.getObservationDiscipline').on("change", changeTypes);
    $('.getObservationType').on("change", changeParameters);

    function delDisc() {
        $(this).parents('.discDiv').first().remove();
    }
    function delType() {
        $(this).parents('.typeDiv').first().remove();
    }
    function delParam() {
        $(this).parents('.paramDiv').first().remove();
    }

    function delScope() {
        $(this).parents('.scopeDiv').first().remove();
    }
    function delGeographicalObj() {
        $(this).parents('.geographicalObjDiv').first().remove();
    }
    function delOrganization() {
        $(this).parents('.organizationDiv').first().remove();
    }

    let getDiscId = 0;
    let getTypeId = 0;
    let getParamId = 0;

    let getScopeId = 0;
    let getGeogrObjId = 0;
    let getOrganizationId = 0;

    function addDisc() {
        let tpl =
            '<div class="discDiv">' +
                '<div class="form-group row mt-3">' +
                    '<label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>' +
                    '<div class="col-sm-9 input-group">' +
                        '<select name="observationDiscipline" class="browser-default custom-select getObservationDiscipline" id="getObservationDiscipline'+ getDiscId +'">' +
                            '<option value="0">-- Выберите дисциплину --</option>' +
                            disciplines +
                        '</select>' +
                        '<div class="input-group-append">' +
                            '<button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delDisc" type="button" id="delDisc'+ getDiscId +'">x</button>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
                '<div class="typeDiv">' +
                    '<div class="form-group row mt-3">' +
                        '<label class="col-sm-3 col-form-label">Вид наблюдений:</label>' +
                        '<div class="col-sm-9">' +
                            '<select name="observationType" class="browser-default custom-select getObservationType" id="getObservationType'+ getTypeId +'" disabled>' +
                                '<option value="0">-- Выберите вид наблюдений --</option>' +
                            '</select>' +
                        '</div>' +
                    '</div>' +
                    '<div class="paramDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                            '<div class="col-sm-9">' +
                                '<select name="observationParameter" class="browser-default custom-select getObservationParameter" id="getObservationParameter'+ getParamId +'" disabled>' +
                                    '<option value="0">-- Выберите параметр наблюдений --</option>' +
                                '</select>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                    '<div class="form-group row mt-3">' +
                        '<a class="col-sm btn btn-primary addParam" id="addParam'+ getParamId +'">Добавить параметр</a>' +
                    '</div>' +
                '</div>' +
                '<div class="form-group row mt-3">' +
                    '<a class="col-sm btn btn-primary addType" id="addType'+ getTypeId +'">Добавить вид</a>' +
                '</div>' +
            '</div>';
        $(this).parents('div').first().before(tpl);

        $('#getObservationDiscipline' + getDiscId).on("change", changeTypes);
        $('#getObservationType' + getTypeId).on("change", changeParameters);

        $('#addType' + getTypeId).on("click", addType);
        $('#addParam'+ getParamId).on("click", addParam);

        $('#delDisc'+ getDiscId).on("click", delDisc);

        getDiscId++;
        getTypeId++;
        getParamId++;
    }

    function addType() {
        let requestAddress = "/getObservationTypeList",
            idDisc = $(this).parents('.discDiv').first().find(".getObservationDiscipline").val(),
            parentDiv = $(this).parents('.discDiv').first(),
            changedObj = 'getObservationType' + getTypeId,
            changedObjHtml = '<option value="0">-- Выберите вид наблюдений --</option>';

        let tpl =   '<div class="typeDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Вид наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationType" class="browser-default custom-select getObservationType" id="'+ changedObj +'" disabled>' +
                                    changedObjHtml +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delDisc" type="button" id="delType'+ getTypeId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="paramDiv">' +
                            '<div class="form-group row mt-3">' +
                                '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                                '<div class="col-sm-9">' +
                                    '<select name="observationParameter" class="browser-default custom-select getObservationParameter" id="getObservationParameter'+ getParamId +'" disabled>' +
                                        '<option value="0">-- Выберите параметр наблюдений --</option>' +
                                    '</select>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group row mt-3">' +
                            '<a class="col-sm btn btn-primary addParam" id="addParam'+ getParamId +'">Добавить параметр</a>' +
                        '</div>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);

        $('#' + changedObj).on("change", changeParameters);

        $('#addParam'+ getParamId).on("click", addParam);

        $('#delType'+ getTypeId).on("click", delType);

        getTypeId++;
        getParamId++;

        if (idDisc != 0) {
            ajaxRequest(requestAddress, idDisc, parentDiv, '#' + changedObj, changedObjHtml);
        }
    }

    function addParam() {
        let requestAddress = "/getObservationParameterList",
            idType = $(this).parents('.typeDiv').first().find(".getObservationType").val(),
            parentDiv = $(this).parents('.typeDiv').first(),
            changedObj = 'getObservationParameter' + getParamId,
            changedObjHtml = '<option value="0">-- Выберите параметр наблюдений --</option>';

        let tpl =   '<div class="paramDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationParameter" class="browser-default custom-select getObservationParameter" id="'+ changedObj +'" disabled>' +
                                    changedObjHtml +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delDisc" type="button" id="delParam'+ getParamId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);

        $('#delParam'+ getParamId).on("click", delParam);

        getParamId++;

        if (idType != 0) {
            ajaxRequest(requestAddress, idType, parentDiv, '#' + changedObj, changedObjHtml);
        }
    }

    function addScope() {
        let tpl =   '<div class="scopeDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Сфера наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="observationScope" class="browser-default custom-select getObservationScope" id="getObservationScope'+ getScopeId +'">' +
                                    '<option value="0">-- Выберите сферу наблюдений --</option>' +
                                    scopes +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delScope" type="button" id="delScope'+ getScopeId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';

        $(this).parents('div').first().before(tpl);

        $('#delScope'+ getScopeId).on("click", delScope);

        getScopeId++;
    }

    function addGeogrObj() {
        let tpl =   '<div class="geographicalObjDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Территория наблюдений:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="geographicalObject" class="browser-default custom-select getGeographicalObject" id="getGeographicalObject' + getGeogrObjId +'">' +
                                    '<option value="0">-- Выберите территорию наблюдений --</option>' +
                                    geogrObjects +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delGeogrObj" type="button" id="delGeogrObj'+ getGeogrObjId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';

        $(this).parents('div').first().before(tpl);

        $('#delGeogrObj'+ getGeogrObjId).on("click", delGeographicalObj);

        getGeogrObjId++;
    }

    function addOrganization() {
        let tpl =   '<div class="organizationDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Организация:</label>' +
                            '<div class="col-sm-9 input-group">' +
                                '<select name="organization" class="browser-default custom-select getOrganization" id="getOrganization'+ getOrganizationId +'">' +
                                    '<option value="0">-- Выберите организацию --</option>' +
                                    organizations +
                                '</select>' +
                                '<div class="input-group-append">' +
                                    '<button class="btn btn-md btn-danger m-0 ml-1 px-3 py-2 delOrganization" type="button" id="delOrganization'+ getOrganizationId +'">x</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>';

        $(this).parents('div').first().before(tpl);

        $('#delOrganization'+ getOrganizationId).on("click", delOrganization);

        getOrganizationId++;
    }

    $('.addDisc').on("click", addDisc);
    $('.addType').on("click", addType);
    $('.addParam').on("click", addParam);

    $('.addScope').on("click", addScope);
    $('.addGeogrObj').on("click", addGeogrObj);
    $('.addOrganization').on("click", addOrganization);
});