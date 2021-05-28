$(document).ready(function () {
    function changeTypes() {
        let id = $(this).val();

        if (id === '0' || id === '-1') {
            $('#getObservationType').html('<option value="0">-- Выберите вид наблюдений --</option>');
            $('#getObservationType').attr('disabled', true);
            return(false);
        }

        $('#getObservationType').attr('disabled', true);
        $('#getObservationType').html('<option>загрузка...</option>');

        $.get(
            "/getObservationTypeList",
            {id: id},
            function (result) {
                if (result.type === 'error')
                {
                    alert('error');
                    return(false);
                }
                else {
                    let options = '';

                    $(result).each(function(key, val)
                    {
                        options += '<option value="' + val.id + '">' + val.name + '</option>';
                    });

                    $('#getObservationType').html('<option value="0">-- Выберите вид наблюдений --</option>' + options);
                    $('#getObservationType').attr('disabled', false);
                }
            },
            "json");
    }

    $('#getObservationDiscipline').on("change", changeTypes);
});