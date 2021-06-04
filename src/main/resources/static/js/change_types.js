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

        $.ajax({
            type: 'GET',
            url: "/getObservationTypeList",
            data: {
                id: id
            },
            dataType: 'json',
            success: [
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
            ],
            error: [
                function () {
                    alert("Не удалось выполнить запрос!");
                }
            ]
        });
    }

    $('#getObservationDiscipline').on("change", changeTypes);
});