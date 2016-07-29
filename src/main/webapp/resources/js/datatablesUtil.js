function makeEditable() {
    $('#add').click(function () {
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });
    
    $('#filterForm').submit(function () {
        filter();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            if (filtered) {
                filter();
            } else {
                updateTable();
            }
            successNoty('Deleted');
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.fnClearTable();
        $.each(data, function (key, item) {
            datatableApi.fnAddData(item);
        });
        datatableApi.fnDraw();
    });
}

function updateRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'PUT',
        success: function () {
            updateTable();
            successNoty('Updated');
        }
    });
}

function save() {
    var form = $('#detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            if (filtered) {
                filter();
            } else {
                updateTable();
            }
            successNoty('Saved');
        }
    });
}

function filter() {
    var form = $('#filterForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: form.serialize(),
        success: function (data) {
            datatableApi.fnClearTable();
            $.each(data, function (key, item) {
                datatableApi.fnAddData(item);
            });
            datatableApi.fnDraw();
            filtered = true;
        }
    })
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
