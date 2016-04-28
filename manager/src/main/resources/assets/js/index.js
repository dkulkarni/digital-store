var searchUrl = '/api/works/search';
var makesUrl = '/api/works/makes';

$(document).ready(function () {
    ko.applyBindings(new MainModel());
})

function MainModel() {
    var self = this;

    self.works = getWorks();
    self.makes = getMakes();
}

function getWorks() {
    var fetchWorksResponse = callGet(searchUrl, "Failed to fetch works", function (response) {
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

function getMakes() {
    var makesResponse = [];
    var makes = callGet(makesUrl, "Failed to fetch works", function (response) {
        return response.msg;
    });
    for (var i = 0; i < makes.filters.length; i++) {
        var make = {};
        make.link = '/api/works/search?make=' + makes.filters[i];
        make.value = makes.filters[i];
        makesResponse.push(make);
    }
    console.log(makesResponse);
    return makesResponse;
}


