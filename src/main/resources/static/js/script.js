/*
console.log("this is js");
const loginUser = (event) => {
  // getting information from the input
     let loginEmail= document.getElementById("email").value;
     let loginPassword= document.getElementById("userPassword").value;

     // check's the input length you can use input min to achieve the same process
     if (loginEmail.length < 1 || loginPassword.length <1 ){
         // stops the page from submitting
         event.preventDefault();
     }
     // Check's if the input and local storage is the same
     if (localStorage.getItem(loginEmail) === loginEmail &&
         localStorage.getItem(loginPassword) === "password"){
         localStorage.setItem("email",loginEmail);
         if(loginEmail === "a@a.com"){
             localStorage.setItem("name","Amrutha");
         }
         if(loginEmail === "v@v.com"){
             localStorage.setItem("name","Vinay");
         }
         if(loginEmail === "b@b.com"){
             localStorage.setItem("name","Bootstrap");
         }
         if(loginEmail === "avyu@mine.com"){
             localStorage.setItem("name","Avyukth");
         }
     }else {
         // stops the page from submitting
         event.preventDefault();
         var errorDiv = document.getElementById("authenticationError");
         var errorPara = document.createElement('p');
         errorPara.innerHTML="Wrong UserId/Password. Please try again";
         console.log("Please try again ");
         errorDiv.appendChild(errorPara);
         console.log(loginEmail);
         console.log(loginPassword);
     }
 }

 loginForm.addEventListener("submit",loginUser)

 if (localStorage.getItem("name").length > 0){
    // Changes the h1 to add the user name
        console.log(localStorage.getItem("name"));
        document.getElementById("userNameDisplay").innerHTML="Welcome "+localStorage.getItem("name");
    }*/
(() => {
      'use strict'

      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      const forms = document.querySelectorAll('.needs-validation')

      // Loop over them and prevent submission
      Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }

          form.classList.add('was-validated')
        }, false)
      }) })();

$(document).ready(function() {
    $('#toggle').click(function() {
       $('.displayToggle').toggle();
    });
});
//
//function wcqib_refresh_quantity_increments() {
//    jQuery("div.quantity:not(.buttons_added), td.quantity:not(.buttons_added)").each(function(a, b) {
//        var c = jQuery(b);
//        c.addClass("buttons_added"), c.children().first().before('<input type="button" value="-" class="minus" />'), c.children().last().after('<input type="button" value="+" class="plus" />')
//    })
//}
//String.prototype.getDecimals || (String.prototype.getDecimals = function() {
//    var a = this,
//        b = ("" + a).match(/(?:\.(\d+))?(?:[eE]([+-]?\d+))?$/);
//    return b ? Math.max(0, (b[1] ? b[1].length : 0) - (b[2] ? +b[2] : 0)) : 0
//}), jQuery(document).ready(function() {
//    wcqib_refresh_quantity_increments()
//}), jQuery(document).on("updated_wc_div", function() {
//    wcqib_refresh_quantity_increments()
//}), jQuery(document).on("click", ".plus, .minus", function() {
//    var a = jQuery(this).closest(".quantity").find(".qty"),
//        b = parseFloat(a.val()),
//        c = parseFloat(a.attr("max")),
//        d = parseFloat(a.attr("min")),
//        e = a.attr("step");
//    b && "" !== b && "NaN" !== b || (b = 0), "" !== c && "NaN" !== c || (c = ""), "" !== d && "NaN" !== d || (d = 0), "any" !== e && "" !== e && void 0 !== e && "NaN" !== parseFloat(e) || (e = 1), jQuery(this).is(".plus") ? c && b >= c ? a.val(c) : a.val((b + parseFloat(e)).toFixed(e.getDecimals())) : d && b <= d ? a.val(d) : b > 0 && a.val((b - parseFloat(e)).toFixed(e.getDecimals())), a.trigger("change")
//});
//
///* cart calculation*/
// $(document).ready(function() {
//
//   /* Set rates */
//   var taxRate = 0.05;
//   var fadeTime = 300;
//
//   /* Assign actions */
//   $('.pass-quantity input').change(function() {
//     updateQuantity(this);
//   });
//
//   $('.remove-item button').click(function() {
//     removeItem(this);
//   });
//
//
//   /* Recalculate cart */
//   function recalculateCart() {
//     var subtotal = 0;
//
//     /* Sum up row totals */
//     $('.item').each(function() {
//       subtotal += parseFloat($(this).children('.product-line-price').text());
//     });
//
//     /* Calculate totals */
//     var tax = subtotal * taxRate;
//     var total = subtotal + tax;
//
//     /* Update totals display */
//     $('.totals-value').fadeOut(fadeTime, function() {
//       $('#cart-subtotal').html(subtotal.toFixed(2));
//       $('#cart-tax').html(tax.toFixed(2));
//       $('.cart-total').html(total.toFixed(2));
//       if (total == 0) {
//         $('.checkout').fadeOut(fadeTime);
//       } else {
//         $('.checkout').fadeIn(fadeTime);
//       }
//       $('.totals-value').fadeIn(fadeTime);
//     });
//   }
//
//
//   /* Update quantity */
//   function updateQuantity(quantityInput) {
//     /* Calculate line price */
//     var productRow = $(quantityInput).parent().parent();
//     var price = parseFloat(productRow.children('.product-price').text());
//     var quantity = $(quantityInput).val();
//     var linePrice = price * quantity;
//
//     /* Update line price display and recalc cart totals */
//     productRow.children('.product-line-price').each(function() {
//       $(this).fadeOut(fadeTime, function() {
//         $(this).text(linePrice.toFixed(2));
//         recalculateCart();
//         $(this).fadeIn(fadeTime);
//       });
//     });
//   }
//
//   /* Remove item from cart */
//   function removeItem(removeButton) {
//     /* Remove row from DOM and recalc cart total */
//     var productRow = $(removeButton).parent().parent();
//     productRow.slideUp(fadeTime, function() {
//       productRow.remove();
//       recalculateCart();
//     });
//   }
//
// });
//
//
//import notEmpty from 'formvalidation/dist/es6/validators/notEmpty';
//
//const result = notEmpty().validate({
//    value: ...,
//    options: {
//        message: ...,
//    },
//});