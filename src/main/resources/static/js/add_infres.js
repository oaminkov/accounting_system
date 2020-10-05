jQuery(document).ready(function () {
    function ajaxRequest(requestAddress, id, parentDiv, changedClass, changedClassHtml) {
        $.get(
            requestAddress,
            {id: id},
            function (result) {
                if (result.type === 'error') {
                    alert('error');
                    return(false);
                }
                else {
                    let options = '';

                    $(result).each(function(key, val) {
                        options += '<option value="' + val.id + '">' + val.name + '</option>';
                    });

                    parentDiv.find(changedClass).html(changedClassHtml + options);
                    parentDiv.find(changedClass).attr('disabled', false);
                }
            },
            "json");
    }

    function changeTypes() {
        let requestAddress = "/getObservationTypeList";
        let id = $(this).val();
        let parentDiv = $(this).parents('.discDiv').first();
        let changedClass = '.getObservationType';
        let changedClassHtml = '<option value="0">-- Выберите вид наблюдений --</option>';

        if (id === '0' || id === '-1') {
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

        ajaxRequest(requestAddress, id, parentDiv, changedClass, changedClassHtml);
    }

    function changeParameters() {
        let requestAddress = "/getObservationParameterList";
        let id = $(this).val();
        let parentDiv = $(this).parents('.typeDiv').first();
        let changedClass = '.getObservationParameter';
        let changedClassHtml = '<option value="0">-- Выберите параметр наблюдений --</option>';

        if (id === '0' || id === '-1') {
            parentDiv.find(changedClass).html(changedClassHtml);
            parentDiv.find(changedClass).attr('disabled', true);
            return(false);
        }

        parentDiv.find(changedClass).attr('disabled', true);
        parentDiv.find(changedClass).html('<option>загрузка...</option>');

        ajaxRequest(requestAddress, id, parentDiv, changedClass, changedClassHtml);
    }

    $('.getObservationDiscipline').on("change", changeTypes);
    $('.getObservationType').on("change", changeParameters);

    let getDiscId = 0;
    let getTypeId = 0;
    let getParamId = 0;

    function addDisc() {
        let tpl =
            '<div class="discDiv">' +
                '<div class="form-group row mt-3">' +
                    '<label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>' +
                    '<div class="col-sm-9">' +
                        '<select name="observationDiscipline" class="browser-default custom-select getObservationDiscipline" id="getObservationDiscipline'+ getDiscId +'">' +
                            '<option value="0">-- Выберите дисциплину --</option>' +
                            disciplines +
                        '</select>' +
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

        getDiscId++;
        getTypeId++;
        getParamId++;
    }

    function addType() {
        let tpl =   '<div class="typeDiv">' +
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
                    '</div>';
        $(this).parents('div').first().before(tpl);

        $('#getObservationType' + getTypeId).on("change", changeParameters);

        $('#addParam'+ getParamId).on("click", addParam);

        getTypeId++;
        getParamId++;
    }

    function addParam() {
        let tpl =   '<div class="paramDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                            '<div class="col-sm-9">' +
                                '<select name="observationParameter" class="browser-default custom-select getObservationParameter" id="getObservationParameter'+ getParamId +'" disabled>' +
                                    '<option value="0">-- Выберите параметр наблюдений --</option>' +
                                '</select>' +
                            '</div>' +
                        '</div>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);

        getParamId++;
    }

    $('.addDisc').on("click", addDisc);
    $('.addType').on("click", addType);
    $('.addParam').on("click", addParam);
});