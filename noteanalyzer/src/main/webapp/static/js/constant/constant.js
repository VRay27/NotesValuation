var app = angular.module('NoteApp');
/*app.constant("noteInputFormModel",{
  typeOfNote : "",
  address : "",
  property : "",
  noteDate : new Date(),
  upb : "",
  rate : "",
  pdiPayment : "",
  tdiPayment : "",
  remainingTerm : ""
});*/
app.constant("noteDetailModel",{
  rate : "",
  payment : "",
  upb : "",
  bedroom : "",
  bath : "",
  sqfoot : "",
  owner : "",
  avgEducation : ""
});

app.constant("noteSummaryModel",{
  assetUrl : "",
  yieldVal : "",
  itv : "",
  ltv : "",
  marketValue : "",
  crime : "",
  overallScore : ""
});

app.constant("loginModel", {
  "username": "",
  "password": ""
});

app.constant("userDetailsModel", {
  userName: "",
  emailId: "",
  password: "",
  contactNumber: ""
});

app.constant('noteUploadAPI', 'api/noteUpload');