<androidx.cardview.widget.CardView
        android:id="@+id/cardView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardCornerRadius="5dp"
        android:elevation="12dp"
        >

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="566dp"
            android:padding="15dp">


            <ImageButton
                android:id="@+id/edit_report"
                android:layout_width="wrap_content"
                android:layout_height="35dp"
                android:backgroundTint="@color/colorPrimaryDark"
                android:src="@android:drawable/ic_menu_edit"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.69"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.892" />

            <ImageButton
                android:id="@+id/delete_report"
                android:layout_width="wrap_content"
                android:layout_height="35dp"
                android:src="@android:drawable/ic_delete"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.919"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.894" />

            <TextView
                android:id="@+id/textView12"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Report Date"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.0"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.05" />

            <TextView
                android:id="@+id/textView9"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Registration number:"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.0"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.165" />

            <TextView
                android:id="@+id/textView10"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="First Name:"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.045"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.239" />

            <TextView
                android:id="@+id/textView11"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Last Name:"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.021"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.336" />

            <TextView
                android:id="@+id/ad_report_studentfname"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_below="@id/ad_report_date"
                android:gravity="center_horizontal"
                android:paddingTop="5dp"
                android:text="hello2"
                android:textAlignment="center"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.293"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.228" />

            <TextView
                android:id="@+id/ad_report_studentlname"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_below="@id/ad_report_studentfname"
                android:gravity="center_horizontal"
                android:paddingTop="5dp"
                android:text="hello2"
                android:textAlignment="center"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.263"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.33" />

            <TextView
                android:id="@+id/ad_report_studentid"
                android:layout_width="44dp"
                android:layout_height="21dp"
                android:layout_below="@id/ad_report_studentlname"
                android:gravity="center_horizontal"
                android:paddingTop="5dp"
                android:text="hello2"
                android:textAlignment="center"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.446"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.15" />

            <TextView
                android:id="@+id/ad_report"
                android:layout_width="match_parent"
                android:layout_height="200dp"
                android:layout_below="@id/ad_report_studentid"
                android:bufferType="spannable"
                android:cursorVisible="true"
                android:inputType="text|textMultiLine|textCapSentences"
                android:isScrollContainer="true"
                android:scrollbarAlwaysDrawVerticalTrack="true"
                android:scrollbarSize="10dp"
                android:scrollbarStyle="insideOverlay"
                android:scrollbars="vertical"
                android:selectAllOnFocus="true"
                android:singleLine="false"
                android:text="Write your Report Here"
                android:verticalScrollbarPosition="right"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/ad_report_studentlname"
                app:layout_constraintVertical_bias="0.171" />

            <TextView
                android:id="@+id/ad_report_date"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:background="@color/design_default_color_primary"
                android:gravity="center_horizontal"
                android:text="May 12 2018"
                android:textAlignment="center"
                android:textSize="18sp"
                android:textStyle="bold"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.447"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintVertical_bias="0.05" />

        </androidx.constraintlayout.widget.ConstraintLayout>


    </androidx.cardview.widget.CardView>