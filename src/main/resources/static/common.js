(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["common"],{

/***/ "./src/app/modules/account/account.module.ts":
/*!***************************************************!*\
  !*** ./src/app/modules/account/account.module.ts ***!
  \***************************************************/
/*! exports provided: AccountModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AccountModule", function() { return AccountModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var ngx_uaa__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ngx-uaa */ "./node_modules/ngx-uaa/fesm5/ngx-uaa.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ngx-translate/core */ "./node_modules/@ngx-translate/core/fesm5/ngx-translate-core.js");
/* harmony import */ var ngx_json_form__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ngx-json-form */ "./node_modules/ngx-json-form/fesm5/ngx-json-form.js");
/* harmony import */ var _account_routing__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ./account.routing */ "./src/app/modules/account/account.routing.ts");
/* harmony import */ var _clr_angular__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @clr/angular */ "./node_modules/@clr/angular/fesm5/clr-angular.js");
/* harmony import */ var _component_my_data_modal_my_data_modal_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./component/my-data-modal/my-data-modal.component */ "./src/app/modules/account/component/my-data-modal/my-data-modal.component.ts");










var AccountModule = /** @class */ (function () {
    function AccountModule(translate, i18nDefaultLang) {
        this.i18nDefaultLang = i18nDefaultLang;
        translate.use(i18nDefaultLang);
    }
    AccountModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _component_my_data_modal_my_data_modal_component__WEBPACK_IMPORTED_MODULE_9__["MyDataModalComponent"]
            ],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_4__["CommonModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(_account_routing__WEBPACK_IMPORTED_MODULE_7__["ACCOUNT_ROUTES"]),
                ngx_json_form__WEBPACK_IMPORTED_MODULE_6__["NgxJsonFormModule"],
                ngx_uaa__WEBPACK_IMPORTED_MODULE_3__["NgxUaaLibModule"],
                _clr_angular__WEBPACK_IMPORTED_MODULE_8__["ClrModalModule"],
            ],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"], _component_my_data_modal_my_data_modal_component__WEBPACK_IMPORTED_MODULE_9__["MyDataModalComponent"]],
            providers: []
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__param"](1, Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Inject"])('i18n_DEFAULT_LANG')),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_ngx_translate_core__WEBPACK_IMPORTED_MODULE_5__["TranslateService"], String])
    ], AccountModule);
    return AccountModule;
}());



/***/ }),

/***/ "./src/app/modules/account/account.routing.ts":
/*!****************************************************!*\
  !*** ./src/app/modules/account/account.routing.ts ***!
  \****************************************************/
/*! exports provided: ACCOUNT_ROUTES */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ACCOUNT_ROUTES", function() { return ACCOUNT_ROUTES; });
var ACCOUNT_ROUTES = [
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    }, {
        path: '**',
        redirectTo: '/home',
        pathMatch: 'full'
    }
];


/***/ }),

/***/ "./src/app/modules/account/component/my-data-modal/my-data-modal.component.html":
/*!**************************************************************************************!*\
  !*** ./src/app/modules/account/component/my-data-modal/my-data-modal.component.html ***!
  \**************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<clr-modal [(clrModalOpen)]=\"opened\" [clrModalSize]=\"'md'\">\r\n  <h3 class=\"modal-title\">I miei dati</h3>\r\n  <div class=\"modal-body\">\r\n\r\n    <lib-ngx-uaa-profile-detail></lib-ngx-uaa-profile-detail>\r\n\r\n  </div>\r\n  <div class=\"modal-footer\">\r\n    <!--    <button type=\"button\" class=\"btn btn-outline\" (click)=\"basic = false\">Cancel</button>\r\n        <button type=\"button\" class=\"btn btn-primary\" (click)=\"basic = false\">Ok</button>-->\r\n  </div>\r\n</clr-modal>\r\n"

/***/ }),

/***/ "./src/app/modules/account/component/my-data-modal/my-data-modal.component.scss":
/*!**************************************************************************************!*\
  !*** ./src/app/modules/account/component/my-data-modal/my-data-modal.component.scss ***!
  \**************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL21vZHVsZXMvYWNjb3VudC9jb21wb25lbnQvbXktZGF0YS1tb2RhbC9teS1kYXRhLW1vZGFsLmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/modules/account/component/my-data-modal/my-data-modal.component.ts":
/*!************************************************************************************!*\
  !*** ./src/app/modules/account/component/my-data-modal/my-data-modal.component.ts ***!
  \************************************************************************************/
/*! exports provided: MyDataModalComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MyDataModalComponent", function() { return MyDataModalComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var MyDataModalComponent = /** @class */ (function () {
    function MyDataModalComponent() {
        this.openedChange = new _angular_core__WEBPACK_IMPORTED_MODULE_1__["EventEmitter"]();
    }
    MyDataModalComponent.prototype.ngOnInit = function () {
        var _this = this;
        setInterval(function () {
            _this.openedChange.emit(_this.opened);
        }, 1000); // TODO workaround
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])(),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", Boolean)
    ], MyDataModalComponent.prototype, "opened", void 0);
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Output"])(),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", Object)
    ], MyDataModalComponent.prototype, "openedChange", void 0);
    MyDataModalComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-my-data-modal',
            template: __webpack_require__(/*! ./my-data-modal.component.html */ "./src/app/modules/account/component/my-data-modal/my-data-modal.component.html"),
            styles: [__webpack_require__(/*! ./my-data-modal.component.scss */ "./src/app/modules/account/component/my-data-modal/my-data-modal.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], MyDataModalComponent);
    return MyDataModalComponent;
}());



/***/ }),

/***/ "./src/app/modules/admin/admin.module.ts":
/*!***********************************************!*\
  !*** ./src/app/modules/admin/admin.module.ts ***!
  \***********************************************/
/*! exports provided: AdminModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AdminModule", function() { return AdminModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var ngx_uaa__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ngx-uaa */ "./node_modules/ngx-uaa/fesm5/ngx-uaa.js");
/* harmony import */ var _admin_routing__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./admin.routing */ "./src/app/modules/admin/admin.routing.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _ngx_translate_core__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ngx-translate/core */ "./node_modules/@ngx-translate/core/fesm5/ngx-translate-core.js");
/* harmony import */ var ngx_json_form__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ngx-json-form */ "./node_modules/ngx-json-form/fesm5/ngx-json-form.js");
/* harmony import */ var _clr_angular__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @clr/angular */ "./node_modules/@clr/angular/fesm5/clr-angular.js");
/* harmony import */ var _component_person_modal_person_modal_component__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./component/person-modal/person-modal.component */ "./src/app/modules/admin/component/person-modal/person-modal.component.ts");










var AdminModule = /** @class */ (function () {
    function AdminModule(translate, i18nDefaultLang) {
        this.i18nDefaultLang = i18nDefaultLang;
        translate.use(i18nDefaultLang);
    }
    AdminModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [
                _component_person_modal_person_modal_component__WEBPACK_IMPORTED_MODULE_9__["PersonModalComponent"]
            ],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_5__["CommonModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(_admin_routing__WEBPACK_IMPORTED_MODULE_4__["ADMIN_ROUTES"]),
                ngx_json_form__WEBPACK_IMPORTED_MODULE_7__["NgxJsonFormModule"],
                ngx_uaa__WEBPACK_IMPORTED_MODULE_3__["NgxUaaLibModule"],
                _clr_angular__WEBPACK_IMPORTED_MODULE_8__["ClrModalModule"]
            ],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"], _component_person_modal_person_modal_component__WEBPACK_IMPORTED_MODULE_9__["PersonModalComponent"]],
            providers: []
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__param"](1, Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Inject"])('i18n_DEFAULT_LANG')),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_ngx_translate_core__WEBPACK_IMPORTED_MODULE_6__["TranslateService"], String])
    ], AdminModule);
    return AdminModule;
}());



/***/ }),

/***/ "./src/app/modules/admin/admin.routing.ts":
/*!************************************************!*\
  !*** ./src/app/modules/admin/admin.routing.ts ***!
  \************************************************/
/*! exports provided: ADMIN_ROUTES */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ADMIN_ROUTES", function() { return ADMIN_ROUTES; });
var ADMIN_ROUTES = [
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    }, {
        path: '**',
        redirectTo: '/home',
        pathMatch: 'full'
    }
];


/***/ }),

/***/ "./src/app/modules/admin/component/person-modal/person-modal.component.html":
/*!**********************************************************************************!*\
  !*** ./src/app/modules/admin/component/person-modal/person-modal.component.html ***!
  \**********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<clr-modal class=\"list-person\" [(clrModalOpen)]=\"opened\" [clrModalSize]=\"'xl'\">\r\n  <h3 class=\"modal-title\">Lista utenti</h3>\r\n  <div class=\"modal-body\">\r\n    <lib-ngx-uaa-person-list></lib-ngx-uaa-person-list>\r\n  </div>\r\n  <div class=\"modal-footer\">\r\n    <!--    <button type=\"button\" class=\"btn btn-outline\" (click)=\"basic = false\">Cancel</button>\r\n        <button type=\"button\" class=\"btn btn-primary\" (click)=\"basic = false\">Ok</button>-->\r\n  </div>\r\n</clr-modal>\r\n"

/***/ }),

/***/ "./src/app/modules/admin/component/person-modal/person-modal.component.scss":
/*!**********************************************************************************!*\
  !*** ./src/app/modules/admin/component/person-modal/person-modal.component.scss ***!
  \**********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".list-person ::ng-deep.modal-dialog {\n  height: 90vh !important;\n  width: 90vw !important; }\n  .list-person ::ng-deep.modal-dialog .modal-content-wrapper .modal-content {\n    /*\r\n        height: 90vh !important;\r\n        width: 90vw !important;\r\n        */ }\n  .list-person ::ng-deep.modal-dialog * {\n    font-size: 0.50rem; }\n  .list-person ::ng-deep table tbody tr td .btn {\n  color: #0079b8; }\n  .list-person ::ng-deep ul {\n  list-style: unset; }\n  .list-person ::ng-deep ul li {\n    display: -webkit-inline-flex;\n    display: inline-flex; }\n\r\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbW9kdWxlcy9hZG1pbi9jb21wb25lbnQvcGVyc29uLW1vZGFsL0o6XFx4YW1wcFxcaHRkb2NzXFxMdWNhXFxuZ3hkcm8tY29tdW5pY28tYmdwb2ludC9zcmNcXGFwcFxcbW9kdWxlc1xcYWRtaW5cXGNvbXBvbmVudFxccGVyc29uLW1vZGFsXFxwZXJzb24tbW9kYWwuY29tcG9uZW50LnNjc3MiLCJzcmMvYXBwL21vZHVsZXMvYWRtaW4vY29tcG9uZW50L3BlcnNvbi1tb2RhbC9wZXJzb24tbW9kYWwuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFZSSx1QkFBdUI7RUFDdkIsc0JBQXNCLEVBQUE7RUFiMUI7SUFLUTs7O1NDRUMsRURDQztFQVJWO0lBZ0JNLGtCQUFrQixFQUFBO0VBaEJ4QjtFQTBCYyxjQUFjLEVBQUE7RUExQjVCO0VBa0NNLGlCQUFpQixFQUFBO0VBbEN2QjtJQW9DUSw0QkFBb0I7SUFBcEIsb0JBQW9CLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9tb2R1bGVzL2FkbWluL2NvbXBvbmVudC9wZXJzb24tbW9kYWwvcGVyc29uLW1vZGFsLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmxpc3QtcGVyc29uIHtcclxuICA6Om5nLWRlZXAubW9kYWwtZGlhbG9nIHtcclxuICAgIC5tb2RhbC1jb250ZW50LXdyYXBwZXIge1xyXG4gICAgICAubW9kYWwtY29udGVudCB7XHJcblxyXG4gICAgICAgIC8qXHJcbiAgICAgICAgaGVpZ2h0OiA5MHZoICFpbXBvcnRhbnQ7XHJcbiAgICAgICAgd2lkdGg6IDkwdncgIWltcG9ydGFudDtcclxuICAgICAgICAqL1xyXG5cclxuICAgICAgfVxyXG4gICAgfVxyXG4gICAgaGVpZ2h0OiA5MHZoICFpbXBvcnRhbnQ7XHJcbiAgICB3aWR0aDogOTB2dyAhaW1wb3J0YW50O1xyXG5cclxuICAgICoge1xyXG4gICAgICBmb250LXNpemU6IDAuNTByZW07XHJcbiAgICB9XHJcbiAgfVxyXG5cclxuICA6Om5nLWRlZXAge1xyXG4gICAgdGFibGUge1xyXG4gICAgICB0Ym9keSB7XHJcbiAgICAgICAgdHIge1xyXG4gICAgICAgICAgdGQge1xyXG4gICAgICAgICAgICAuYnRuIHtcclxuICAgICAgICAgICAgICBjb2xvcjogIzAwNzliODtcclxuICAgICAgICAgICAgfVxyXG4gICAgICAgICAgfVxyXG4gICAgICAgIH1cclxuICAgICAgfVxyXG4gICAgfVxyXG5cclxuICAgIHVsIHtcclxuICAgICAgbGlzdC1zdHlsZTogdW5zZXQ7XHJcbiAgICAgIGxpIHtcclxuICAgICAgICBkaXNwbGF5OiBpbmxpbmUtZmxleDtcclxuICAgICAgfVxyXG4gICAgfVxyXG4gIH1cclxuXHJcbn1cclxuIiwiLmxpc3QtcGVyc29uIDo6bmctZGVlcC5tb2RhbC1kaWFsb2cge1xuICBoZWlnaHQ6IDkwdmggIWltcG9ydGFudDtcbiAgd2lkdGg6IDkwdncgIWltcG9ydGFudDsgfVxuICAubGlzdC1wZXJzb24gOjpuZy1kZWVwLm1vZGFsLWRpYWxvZyAubW9kYWwtY29udGVudC13cmFwcGVyIC5tb2RhbC1jb250ZW50IHtcbiAgICAvKlxyXG4gICAgICAgIGhlaWdodDogOTB2aCAhaW1wb3J0YW50O1xyXG4gICAgICAgIHdpZHRoOiA5MHZ3ICFpbXBvcnRhbnQ7XHJcbiAgICAgICAgKi8gfVxuICAubGlzdC1wZXJzb24gOjpuZy1kZWVwLm1vZGFsLWRpYWxvZyAqIHtcbiAgICBmb250LXNpemU6IDAuNTByZW07IH1cblxuLmxpc3QtcGVyc29uIDo6bmctZGVlcCB0YWJsZSB0Ym9keSB0ciB0ZCAuYnRuIHtcbiAgY29sb3I6ICMwMDc5Yjg7IH1cblxuLmxpc3QtcGVyc29uIDo6bmctZGVlcCB1bCB7XG4gIGxpc3Qtc3R5bGU6IHVuc2V0OyB9XG4gIC5saXN0LXBlcnNvbiA6Om5nLWRlZXAgdWwgbGkge1xuICAgIGRpc3BsYXk6IGlubGluZS1mbGV4OyB9XG4iXX0= */"

/***/ }),

/***/ "./src/app/modules/admin/component/person-modal/person-modal.component.ts":
/*!********************************************************************************!*\
  !*** ./src/app/modules/admin/component/person-modal/person-modal.component.ts ***!
  \********************************************************************************/
/*! exports provided: PersonModalComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PersonModalComponent", function() { return PersonModalComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");


var PersonModalComponent = /** @class */ (function () {
    function PersonModalComponent() {
        this.openedChange = new _angular_core__WEBPACK_IMPORTED_MODULE_1__["EventEmitter"]();
    }
    PersonModalComponent.prototype.ngOnInit = function () {
        var _this = this;
        setInterval(function () {
            _this.openedChange.emit(_this.opened);
        }, 1000); // TODO workaround
    };
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Input"])(),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", Boolean)
    ], PersonModalComponent.prototype, "opened", void 0);
    tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Output"])(),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:type", Object)
    ], PersonModalComponent.prototype, "openedChange", void 0);
    PersonModalComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-person-modal',
            template: __webpack_require__(/*! ./person-modal.component.html */ "./src/app/modules/admin/component/person-modal/person-modal.component.html"),
            styles: [__webpack_require__(/*! ./person-modal.component.scss */ "./src/app/modules/admin/component/person-modal/person-modal.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], PersonModalComponent);
    return PersonModalComponent;
}());



/***/ })

}]);
//# sourceMappingURL=common.js.map