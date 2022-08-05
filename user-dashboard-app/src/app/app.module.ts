import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { UserService } from './user.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ModalComponent } from './modal/modal.component';

@NgModule({
  declarations: [AppComponent, ModalComponent],
  imports: [BrowserModule, HttpClientModule, NgbModule],
  entryComponents: [ModalComponent],
  providers: [UserService],
  bootstrap: [AppComponent],
})
export class AppModule {}
