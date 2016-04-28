var searchUrl = '/api/works/search?make=$make';
var modelsUrl = '/api/works/models?make=$make'

$(document).ready(function () {
    $('#navbar').load("../navbar.html");
    ko.applyBindings(new MakesModel());
})

function MakesModel() {
    var self = this;
    var make = getParam("make");

    self.works = getWorks(make);
    self.models = getModels(make);
}

function getWorks(make) {
    var fetchWorksResponse = callGet(searchUrl.replace('$make', make), "Failed to fetch works", function (response) {
        return response.msg;
    });
    var works = [];
    for (var i = 0; i < fetchWorksResponse.work.length; i++) {
        var res = fetchWorksResponse.work;
        var work = {};
        work.img = res[i].url[0].value;
        work.make = res[i].exif.make;
        work.model = res[i].exif.model;
        works.push(work);
    }
    return works;
}

function getModels(make) {
    var modelsResponse = [];
    var models = callGet(modelsUrl.replace('$make', make), "Failed to fetch models", function (response) {
        return response.msg;
    });
    for (var i = 0; i < models.filters.length; i++) {
        var model = {};
        model.link = 'models.html?model=' + models.filters[i] + "&make=" + make;
        model.value = models.filters[i];
        modelsResponse.push(model);
    }
    return modelsResponse;
}

