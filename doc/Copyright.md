Our app is used to help people borrow books from each other, and lend spare books that a owner doesn't need. While creating our UI and interactions we studied popular apps such as weChat and Uber.

Key terms and generalized rules are listed below (provisional 15/2/2019):   

#### Swap 
This is the job requested by the [Borrower](#r1) and fulfilled by the [Owner](#p1). There is one task, but not all of its attributes will be shown between the Owner and Borrower. A task will hold the following items:
1. _Owner name_ - name of Owner available to both Owner and Borrower.
1. _Borrower name_ - name of the Borrower avaiblabe to both the Owner and Borrower
1. _Title_  - Title of a book available to both Owner and Provider. Owner adds books with titles (mandatory). 
1. _Description_ - available to both Owner and Borrower.  Owner inputs. 
1. _Status_ - Special conditional state the boook is in. Available to both Owner and Borrower. Managed by system. 
1. _Address_ (optional) - Address of the meetup location available to both Owner and Borrower.  Owner inputs upon accepting. 
1. _GPS Location_ (optional) - GPS location available to both Owner and Borrower to be seen on Google Maps.  Owner inputs. 
1. _Location Comment_ (optional) - A comment string available to both the Owner and the Borrower. Owner inputs.

A Owner may:
   * Edit a Swap's location (Address, GPS Location, Location Comment)
   * Cancel a swap
   * Start a swap

A Borrower may: 
   * Confirm a swap is made

#### Book
A book is a/the object to be swaped. Only a owner can create books. A book has the following attributes.
1. _Book Owner_ - Automatically created when a owner puts a new book.
1. _Book Borrower_ Automatically generated when the book is borrowed.
1. _Book Title_ Title of book set by owner.
1. _Book Author_ Author of the book set by owner.
1. _Book Photo_ (optional) - a photo of a book set by owner.
1. _Book Barcode_ (optional) - optional barcode set by owner. 
1. _Book Description_ (optional) - Short description of the book
1. _Book Status_ -  status of a book set by the system
    1. _Available_ - status in which the books can be edited or deleted by owner. 
    1. _Requested_ - status of a book when one or more borrowers requests for a swap.
    1. _Accepted_ - status of a book when the owner accepts a swap request
    1. _Borrowed_ - status of a book when the book is borrowed by a borrower. 

#### Book Status
A book can be one of four states and will typically follow a transition of 'Available'->'Requested'->'Accepted'->'Borrowed'->'Available'. Details follow:  
1. _Available_ - Occurs when a book is available to be swapped. Indicates that there are no potential borrowers for this book. Borrowers may request to swap for this book at this time. The owner can edit or delete the book at this time.
1. _Requested_ - Occurs when a book is being requested by one or more borrowers. Borrowers may request for this book. Owners can deny borrowers, and can accept a single borrower. Upon accepting a single borrower, the rest are automatically denied.
1. _Accepted_ - Occurs when the owner accepts a swap request, and initiates a swap. Borrowers that are not part of the swap can not see this book at this time.
1. _Borrowed_ - Occurs when the book is borrowed by a borrower. 


#### User
A user is any user who is logged into our system. They can be in either of the two states:  
* <a name="r1">**Borrower**</a> -  A state in which the user can view books, reqest for available or requested books, and confirm swaps, and review owners.
*  <a name="p1">**Owner**</a> -  A state in which the user can create new books, edit existing books, accept and deny requests, set up swaps, and review borrowers.

A user will be in one of these two "states" at any given time, shown by the tab they are in at the bottom of any screen inside the app. 

* **Owner View**: The UI options and actions available to a user is a Owner
    * UI VIEWS
        * **Owner Main view** - Main view leading to various sub tabs
            * _Available_ - detailed UI view for available books. Add new books here
            * _Accepted_ - detailed UI and fields for accepted books
            * _Requested_ - detailed UI and fields for books in requested state
            * _Borrowed_ - detailed UI and fields for books in borrowed state
        * **Owner book view** -  View that shows detailed fields for books
        * **Owner Edit Book view** - View for the editing of existing books
        * **Owner View Requests view** View for all requests on a given book
        * **Owner map view** View to edit or look at the location and map
        * **Owner swap view** View to update or set up the swap.
        * **Owner book scan view** View to scan a book's barcode. Function depends on useage location

    * ACTIONS
        * **Add Book** The button click in Available menu to add a new book. Only available in the Owner Available View.
        * **Delete Book** The button click to delete an existing book. Found in Owner Edit Book View
        * **Edit Book** The button click on the books item to enter edit book view. Only Available in Owner Book View.
        * **View Requests** The button click that shows all users that requested this book. Only Available in Owner Requested View
        * **Decline Request** The button click that declines a given request. Only available in the Owner view requests View.
        * **Accept Request** The button click that accepts a given request. Only available in the Owner view requests View.
        * **Add location** The button that saves the state of the current gps tag, along with comments. Only available in Owner map view.
        * **Accept bookswap** The button that acts as the final confirmation to change a book from "requested" to "accepted". Only available in Owner swap view.
        * **Scan book** General action button that leads to the scan book view. Found in multiple locations for any user.

* **Borrower View**: The UI options and actions available to a user is a Borrower
    * UI VIEWS
        * **Borrower Main view** - Main view leading to various sub tabs
            * _Available_ - detailed UI view for available books. Add new books here
            * _Accepted_ - detailed UI and fields for accepted books
            * _Requested_ - detailed UI and fields for books in requested state
            * _Borrowed_ - detailed UI and fields for books in borrowed state
        * **Borrower book view** -  View that shows detailed fields for books
        * **Borrower book search view** View displayed when searching for a book using keywords
        * **Borrower map view** View to look at the location and map
        * **Borrower swap view** View to set up the return swap.
        * **Borrower book scan view** View to scan a book's barcode. Function depends on useage location

    * ACTIONS
        * **View Book** The button click in Available menu to view a existing book. Available to all users.
        * **View Requests (B)** The button click that shows outgoing requests. Available to Borrower.
        * **Request for book** Button click that sends a request for a given book. Avaiable to Borrower.
        * **Scan book** General action button that leads to the scan book view. Found in multiple locations for any user.

* **Profile View**: Globally avaible unique state to view and edit your profile

    * Actions
       * **Edit PFP** Button click that allows to add or edit a photo (profile pic / pfp)
       * **Edit Profile** Button click to save changes to text fields (phone, email etc.)

#### Wow Factor
To help build trust between our task Borrowers and Owners, we feature the following functionality
* **Rating and Reviews** - This allows the book Owners to review the book borrower, and vice versa <br />
* Futher functionalities pending.


<br /> <br /> <br /> 

#### Copyright (C) 2019 Jia Chen Liu, Bowen Hu, Yun Cao, Yifu Chen, Hongbo Zhong, Chaoran Meng

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



