jQuery(document).ready(function () {
    function changeTypes() {
        let id = $(this).val();
        let discDiv = $(this).parents('.discDiv').first();

        if (id === '0' || id === '-1') {
            discDiv.find('.getObservationType').html('<option value="0">-- Выберите вид наблюдений --</option>');
            discDiv.find('.getObservationType').attr('disabled', true);
            discDiv.find('.getObservationParameter').html('<option value="0">-- Выберите параметр наблюдений --</option>');
            discDiv.find('.getObservationParameter').attr('disabled', true);
            return(false);
        }

        discDiv.find('.getObservationType').attr('disabled', true);
        discDiv.find('.getObservationType').html('<option>загрузка...</option>');
        discDiv.find('.getObservationParameter').html('<option value="0">-- Выберите параметр наблюдений --</option>');
        discDiv.find('.getObservationParameter').attr('disabled', true);

        $.get(
            "/getObservationTypeList",
            {idObservationDiscipline: id},
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

                    discDiv.find('.getObservationType').html('<option value="0">-- Выберите вид наблюдений --</option>' + options);
                    discDiv.find('.getObservationType').attr('disabled', false);
                }
            },
            "json");
    }

    function changeParameters() {
        let id = $(this).val();
        let typeDiv = $(this).parents('.typeDiv').first();

        if (id === '0' || id === '-1') {
            typeDiv.find('.getObservationParameter').html('<option value="0">-- Выберите параметр наблюдений --</option>');
            typeDiv.find('.getObservationParameter').attr('disabled', true);
            return(false);
        }

        typeDiv.find('.getObservationParameter').attr('disabled', true);
        typeDiv.find('.getObservationParameter').html('<option>загрузка...</option>');

        $.get(
            "/getObservationParameterList",
            {idObservationType: id},
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

                    typeDiv.find('.getObservationParameter').html('<option value="0">-- Выберите параметр наблюдений --</option>' + options);
                    typeDiv.find('.getObservationParameter').attr('disabled', false);
                }
            },
            "json");
    }

    $('.getObservationDiscipline').on("change", changeTypes);
    $('.getObservationType').on("change", changeParameters);

    function addDisc() {
        let tpl =   '<div class="discDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Дисциплина наблюдений:</label>' +
                            '<div class="col-sm-9">' +
                                '<select name="observationDiscipline" class="browser-default custom-select getObservationDiscipline">' +
                                    '<option value="0">-- Выберите дисциплину --</option>' +
                                    disciplines +
                                '</select>' +
                            '</div>' +
                        '</div>' +
                        '<div class="typeDiv">' +
                            '<div class="form-group row mt-3">' +
                                '<label class="col-sm-3 col-form-label">Вид наблюдений:</label>' +
                                '<div class="col-sm-9">' +
                                    '<select name="observationType" class="browser-default custom-select getObservationType" disabled>' +
                                        '<option value="0">-- Выберите вид наблюдений --</option>' +
                                    '</select>' +
                                '</div>' +
                            '</div>' +
                            '<div class="paramDiv">' +
                                '<div class="form-group row mt-3">' +
                                    '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                                    '<div class="col-sm-9">' +
                                        '<select name="observationParameter" class="browser-default custom-select getObservationParameter" disabled>' +
                                            '<option value="0">-- Выберите параметр наблюдений --</option>' +
                                        '</select>' +
                                    '</div>' +
                                '</div>' +
                            '</div>' +
                            '<div class="form-group row mt-3">' +
                                '<a class="col-sm btn btn-primary addParam">Добавить параметр</a>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group row mt-3">' +
                            '<a class="col-sm btn btn-primary addType">Добавить вид</a>' +
                        '</div>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);

        $('.getObservationDiscipline').off("change", changeTypes);
        $('.getObservationType').off("change", changeParameters);
        $('.getObservationDiscipline').on("change", changeTypes);
        $('.getObservationType').on("change", changeParameters);

        $('.addType').off("click", addType);
        $('.addParam').off("click", addParam);
        $('.addType').on("click", addType);
        $('.addParam').on("click", addParam);
    }

    function addType() {
        let tpl =   '<div class="typeDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Вид наблюдений:</label>' +
                            '<div class="col-sm-9">' +
                                '<select name="observationType" class="browser-default custom-select getObservationType" disabled>' +
                                    '<option value="0">-- Выберите вид наблюдений --</option>' +
                                '</select>' +
                            '</div>' +
                        '</div>' +
                        '<div class="paramDiv">' +
                            '<div class="form-group row mt-3">' +
                                '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                                '<div class="col-sm-9">' +
                                    '<select name="observationParameter" class="browser-default custom-select getObservationParameter" disabled>' +
                                        '<option value="0">-- Выберите параметр наблюдений --</option>' +
                                    '</select>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="form-group row mt-3">' +
                            '<a class="col-sm btn btn-primary addParam">Добавить параметр</a>' +
                        '</div>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);

        $('.getObservationType').off("change", changeParameters);
        $('.getObservationType').on("change", changeParameters);

        $('.addParam').off("click", addParam);
        $('.addParam').on("click", addParam);
    }

    function addParam() {
        let tpl =   '<div class="paramDiv">' +
                        '<div class="form-group row mt-3">' +
                            '<label class="col-sm-3 col-form-label">Параметр наблюдений:</label>' +
                            '<div class="col-sm-9">' +
                                '<select name="observationParameter" class="browser-default custom-select getObservationParameter" disabled>' +
                                    '<option value="0">-- Выберите параметр наблюдений --</option>' +
                                '</select>' +
                            '</div>' +
                        '</div>' +
                    '</div>';
        $(this).parents('div').first().before(tpl);
    }

    $('.addDisc').on("click", addDisc);
    $('.addType').on("click", addType);
    $('.addParam').on("click", addParam);
});