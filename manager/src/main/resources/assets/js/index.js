var url = '/api/works/search';

$(document).ready(function () {
    ko.applyBindings(new MainModel());
})
function MainModel() {
    var self = this;
    var works = getWorks();
    console.log(works);
}

function getWorks() {
    return callGet(url, "Failed to fetch works", function (response) {
        return response.msg;
    })
}

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