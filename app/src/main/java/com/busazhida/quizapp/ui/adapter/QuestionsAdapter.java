package com.busazhida.quizapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.busazhida.quizapp.R;
import com.busazhida.quizapp.data.call_back.OnButtonAnswerClick;
import com.busazhida.quizapp.data.call_back.OnResultAnswerClickListener;
import com.busazhida.quizapp.data.models.QuestionsModel;
import com.busazhida.quizapp.databinding.QuestionsItemBinding;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    private List<QuestionsModel> questionModels = new ArrayList<>();
    private OnResultAnswerClickListener answerClick;

    public void setAnswerClick(OnResultAnswerClickListener answerClick) {
        this.answerClick = answerClick;
    }

    public void setQuestions(List<QuestionsModel> questionModels) {
        this.questionModels = questionModels;
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsItemBinding binding = QuestionsItemBinding.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_item, parent, false));
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(questionModels.get(position));
    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnButtonAnswerClick {
        public static final int CORRECT_ANSWER = 1;
        public static final int CORRECT_ANSWER_AND_FINAL_ANSWER = 11;
        public static final int WRONG_ANSWER = 2;
        public static final int WRONG_ANSWER_AND_FINAL_ANSWER = 22;
        private final QuestionsItemBinding item;
        private final Vibrator vibrator;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull QuestionsItemBinding itemView) {
            super(itemView.getRoot());
            item = itemView;
            item.setHandlers(this);
            vibrator = (Vibrator) itemView.getRoot().getContext().getSystemService(Context.VIBRATOR_SERVICE);
        }

        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void onBind(QuestionsModel question) {
            item.btn1.setBackgroundResource(R.drawable.btn_custom_bg);
            item.btn2.setBackgroundResource(R.drawable.btn_custom_bg);
            item.btn3.setBackgroundResource(R.drawable.btn_custom_bg);
            item.btn4.setBackgroundResource(R.drawable.btn_custom_bg);
            item.btn5.setBackgroundResource(R.drawable.btn_custom_bg);
            item.btn6.setBackgroundResource(R.drawable.btn_custom_bg);

            item.btn1.setTextColor(Color.BLUE);
            item.btn2.setTextColor(Color.BLUE);
            item.btn3.setTextColor(Color.BLUE);
            item.btn4.setTextColor(Color.BLUE);
            item.btn5.setTextColor(Color.BLUE);
            item.btn6.setTextColor(Color.BLUE);

            question.getIsSkipClicked().observeForever(isSkip -> {
                if (isSkip) {
                    buttonClickable(false);
                    showCorrectButton(question);
                }
            });

            if (question.isChoice()) {
                switch (question.getUserChoice()) {
                    case 0:
                        item.btn1.setBackgroundResource(R.drawable.item_button_3);
                        item.btn1.setTextColor(Color.WHITE);
                        item.btn5.setBackgroundResource(R.drawable.item_button_3);
                        item.btn5.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        item.btn2.setBackgroundResource(R.drawable.item_button_3);
                        item.btn2.setTextColor(Color.WHITE);
                        item.btn6.setBackgroundResource(R.drawable.item_button_3);
                        item.btn6.setTextColor(Color.WHITE);
                        break;
                    case 2:
                        item.btn3.setBackgroundResource(R.drawable.item_button_3);
                        item.btn3.setTextColor(Color.WHITE);
                        break;
                    case 3:
                        item.btn4.setBackgroundResource(R.drawable.item_button_3);
                        item.btn4.setTextColor(Color.WHITE);
                        break;
                }
                showCorrectButton(question);
                buttonClickable(false);
            } else {
                buttonClickable(true);
            }
            item.setQuestion(question);
        }

        private void showCorrectButton(QuestionsModel question) {
            String correctAnc = question.getCorrectAnswer();
            int positionCorrectAnc = 0;
            for (int i = 0; i < question.getIncorrectAnswers().size(); i++) {
                if (correctAnc.equals(question.getIncorrectAnswers().get(i)))
                    positionCorrectAnc = i;
            }
            switch (positionCorrectAnc) {
                case 0:
                    item.btn1.setBackgroundResource(R.drawable.item_button_2);
                    item.btn1.setTextColor(Color.WHITE);
                    item.btn5.setBackgroundResource(R.drawable.item_button_2);
                    item.btn5.setTextColor(Color.WHITE);
                    break;
                case 1:
                    item.btn2.setBackgroundResource(R.drawable.item_button_2);
                    item.btn2.setTextColor(Color.WHITE);
                    item.btn6.setBackgroundResource(R.drawable.item_button_2);
                    item.btn6.setTextColor(Color.WHITE);
                    break;
                case 2:
                    item.btn3.setBackgroundResource(R.drawable.item_button_2);
                    item.btn3.setTextColor(Color.WHITE);
                    break;
                case 3:
                    item.btn4.setBackgroundResource(R.drawable.item_button_2);
                    item.btn4.setTextColor(Color.WHITE);
                    break;
            }

        }

        @Override
        public void onClick(View view, int positionAnswer) {
            item.getQuestion().setChoice(true);
            item.getQuestion().setUserChoice(positionAnswer);
            Button button = (Button) view;
            int result;
            QuestionsModel questionModel = Objects.requireNonNull(questionModels.get(getAdapterPosition()));
            String userAnswer = questionModel.getIncorrectAnswers().get(positionAnswer);
            if (userAnswer.equals(questionModel.getCorrectAnswer())) {
                button.setTextColor(Color.WHITE);
                button.setBackgroundResource(R.drawable.item_button_2);
                if (getAdapterPosition() >= questionModels.size() - 1)
                    result = CORRECT_ANSWER_AND_FINAL_ANSWER;
                else result = CORRECT_ANSWER;
                vibration(50);
            } else {
                button.setTextColor(Color.WHITE);
                button.setBackgroundResource(R.drawable.item_button_3);
                if (getAdapterPosition() >= questionModels.size() - 1)
                    result = WRONG_ANSWER_AND_FINAL_ANSWER;
                else result = WRONG_ANSWER;
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(5)
                        .playOn(view);
                vibration(400);
            }
            buttonClickable(false);
            showCorrectButton(item.getQuestion());
            answerClick.onClick(result, item.getQuestion().getIncorrectAnswers().get(positionAnswer));
        }

        private void buttonClickable(boolean clickable) {
            item.btn1.setClickable(clickable);
            item.btn2.setClickable(clickable);
            item.btn3.setClickable(clickable);
            item.btn4.setClickable(clickable);
            item.btn5.setClickable(clickable);
            item.btn6.setClickable(clickable);
        }

        private void vibration(int count) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                vibrator.vibrate(VibrationEffect.createOneShot(count, VibrationEffect.DEFAULT_AMPLITUDE));
            else vibrator.vibrate(count);
        }
    }
}
