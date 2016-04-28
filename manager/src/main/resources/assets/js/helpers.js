var Response = function (status, msg) {
    var self = this;
    self.status = status;
    self.msg = msg;

    self.isOk = function () {
        if (status == "ok")
            return true;
        return false;
    }
}

function getEmptyResponse() {
    return new Response("fail", null);
}

function httpCall(type, dataType, url, data, errMsg, callBack) {
    var response = getEmptyResponse();
    $.ajax({
        type: type,
        url: url,
        data: data,
        async: false,
        cache: false,
        contentType: 'application/json',
        dataType: dataType,
        success: function (replyJson) {
            response = new Response("ok", replyJson);
        },
        error: function (err, textStatus, errorThrown) {
            var msg = getErrorMessage(err);
            response = new Response("fail", msg);
        }
    });

    if (!response.isOk()) {
        if (errMsg) {
            handleRequestFailure(errMsg + (response.msg != null ? ":" + response.msg : ""));
        }
    } else {
        return callBack(response);
    }
}

function callGet(url, errMsg, callBack) {
    return httpCall('GET', 'json', url, "", errMsg, callBack);
}

function getErrorMessage(error) {
    if (error.status == 404) {
        return "No results found matching the search criteria!";
    }
    if (error.status == 422 || error.status == 409 || error.status == 400) {
        return error.responseText;
    }
    else {
        $("#message").text("Request Failed. Try refreshing the page");
    }
}

function handleRequestCompletion(message) {
    $("#message").removeClass("alert-error").addClass("alert alert-success").text(message);
    window.setTimeout(function () {
        clearStatusMessage();
    }, 3000);
}

function handleRequestFailure(message) {
    $('#message').removeClass().addClass("alert alert-danger").text(message);
}

function clearStatusMessage() {
    $('#message').removeClass("alert alert-danger").empty();
}

function handleWarning(message) {
    $('#message').addClass("alert alert-warning").text(message);
}

function getParam(name) {
    return (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search)) ?
        decodeURIComponent(name[1]) :
        "";
}