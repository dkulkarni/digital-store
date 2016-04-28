var searchUrl = '/api/works/search?make=$make&model=$model';

$(document).ready(function () {
    ko.applyBindings(new MakesModel());
})

function MakesModel() {
    var self = this;
    var make = getParam("make");
    var model = getParam("model");

    self.works = getWorks(make, model);
    self.make = make;
    self.makesLink = './makes.html?make=' + make;
}

function getWorks(make, model) {
    var fetchWorksResponse = callGet(searchUrl.replace('$make', make).replace('$model', model), "Failed to fetch works", function (response) {
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

