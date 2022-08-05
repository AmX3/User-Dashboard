import { UserService } from './user.service';
import { Component, OnInit } from '@angular/core';
import { User } from './user';
import { HttpErrorResponse } from '@angular/common/http';
import { ModalComponent } from './modal/modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  public title = '/';
  //   contains our variables
  public users: User[] = [];

  //   dependency injection of service into the layer
  constructor(
    private userService: UserService,
    private modalService: NgbModal
  ) {}

  //   Implementing the interface onInit and when our component is initialized it will call our getUsers function
  ngOnInit() {
    this.getUsers();
  }

  //   subscribe notifies us when data comes back from the server
  public getUsers(): void {
    this.userService.getUsers().subscribe(
      (Response: User[]) => {
        this.users = Response;
      },

      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  //   public onOpenModel(user: User, mode: string): void {
  //     const button = document.createEme
  //   }
  public open() {
    const modalRef = this.modalService.open(ModalComponent);
    modalRef.componentInstance.modalTitle = 'Edit Employee';
    modalRef.componentInstance.modalContent = '<h1>Hello</h1>';
  }
}
